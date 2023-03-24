package ru.az.mz.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.DepartmentDtoV1;
import ru.az.mz.dto.v1.DepartmentDtoV2;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Department;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;
import ru.az.mz.repositories.DepartmentRepo;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.repositories.OrganizationRepo;
import ru.az.mz.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class DepartmentServiceV1Impl implements DepartmentServiceV1 {

    private final DepartmentRepo departmentRepo;
    private final SecurityService securityService;
    private final OrganizationServiceV1 organizationServiceV1;
    private final OrganizationRepo organizationRepo;
    private final EmployeeRepo employeeRepo;
    private final SetupParameters setupParameters;

    public DepartmentServiceV1Impl(
            DepartmentRepo departmentRepo,
            SecurityService securityService,
            OrganizationServiceV1 organizationServiceV1,
            OrganizationRepo organizationRepo, EmployeeRepo employeeRepo, SetupParameters setupParameters) {
        this.departmentRepo = departmentRepo;
        this.securityService = securityService;
        this.organizationServiceV1 = organizationServiceV1;
        this.organizationRepo = organizationRepo;
        this.employeeRepo = employeeRepo;
        this.setupParameters = setupParameters;
    }

    @Override
    public Page<Department> findAllByName(String name, Pageable pageable) {
        return departmentRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    public Page<Department> findAll(PageRequestDtoV1 pageRequestDtoV1) throws MyException {
        PageRequest pageRequest;
        Page<Department> departments;
        if (pageRequestDtoV1 == null) {
            pageRequest = setupParameters.getPageRequestDefault();
            departments = departmentRepo.findAllByStatus(EntityStatus.ACTIVE, pageRequest);
        } else {
            if (!"".equalsIgnoreCase(pageRequestDtoV1.getSortBy())) {
                pageRequest = PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize(), Sort.by("name"));
                if (pageRequestDtoV1.getParentId() > 0) {
                    Organization organization = organizationServiceV1.findById(pageRequestDtoV1.getParentId());
                    departments = departmentRepo.findAllByNameStartingWithAndOrganizationAndStatus(
                            pageRequestDtoV1.getSearch().trim(),
                            organization,
                            EntityStatus.ACTIVE,
                            pageRequest.withSort(Sort.by("name"))
                    );
                } else {
                    List<Organization> orgs = organizationServiceV1.findAll();
                    if (orgs == null || orgs.size() == 0) {
                        throw new NotFoundException("Organization empty", HttpStatus.NOT_FOUND);
                    } else {
                        departments = departmentRepo.findAllByNameStartingWithAndOrganizationAndStatus(
                                pageRequestDtoV1.getSearch().trim(),
                                orgs.get(0),
                                EntityStatus.ACTIVE,
                                pageRequest.withSort(Sort.by("name"))
                        );
                    }
                }
            } else {
                pageRequest = PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
                if (pageRequestDtoV1.getParentId() > 0) {
                    Organization organization = organizationServiceV1.findById(pageRequestDtoV1.getParentId());
                    departments = departmentRepo.findAllByOrganizationAndStatus(
                            organization,
                            EntityStatus.ACTIVE,
                            pageRequest.withSort(Sort.by("name"))
                    );
                } else {
                    List<Organization> orgs = organizationServiceV1.findAll();
                    if (orgs == null || orgs.size() == 0) {
                        throw new NotFoundException("Organization empty", HttpStatus.NOT_FOUND);
                    } else {
                        departments = departmentRepo.findAllByOrganizationAndStatus(
                                orgs.get(0),
                                EntityStatus.ACTIVE,
                                pageRequest.withSort(Sort.by("name"))
                        );
                    }
                }
            }
        }
        return departments;
    }

    @Override
    @Transactional
    public List<Department> findAllByOrgId(Long id) throws MyException {
        List<Department> deps = new ArrayList<>();
        organizationRepo.findById(id).ifPresent(organization ->
                deps.addAll(
                        departmentRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE).stream()
                            .sorted(Comparator.comparing(Department::getName))
                            .collect(Collectors.toList())
                )
        );
        return deps;
    }

    private void fillDepartment(DepartmentDtoV2 departmentDto, Department department) throws MyException {
        department.setName(departmentDto.getName());
        department.setTopLevel(departmentDto.isTopLevel());

        Long bossId = departmentDto.getBossId() != null && employeeRepo.existsById(departmentDto.getBossId())
                ? departmentDto.getBossId()
                : null;
        department.setBossId(bossId);
        department.setSaveByUser(securityService.getUsername());
        department.setOrganization(
                departmentDto.getOrgId() == null
                        ? null
                        : organizationRepo.findById(departmentDto.getOrgId())
                        .orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND))
        );
        Long parentId = !departmentDto.isTopLevel() && departmentDto.getParentId() != null && departmentRepo.existsById(departmentDto.getParentId())
                ? departmentDto.getParentId()
                : null;
        department.setParentId(parentId);
    }

    @Override
    @Transactional
    public Department add(DepartmentDtoV2 departmentDto) throws MyException {
        Department department = new Department();
        fillDepartment(departmentDto, department);
        return departmentRepo.save(department);
    }

    @Override
    @Transactional
    public Department update(DepartmentDtoV2 departmentDto) throws MyException {
        Department department = findById(departmentDto.getId());
        fillDepartment(departmentDto, department);
        return departmentRepo.save(department);
    }

    @Override
    public boolean delete(long id) throws MyException {
        Department department = findById(id);
        department.setStatus(EntityStatus.DELETED);
        department.setSaveByUser(securityService.getUsername());
        departmentRepo.save(department);
        return true;
    }

    @Override
    public Department findById(Long id) throws MyException {
        return departmentRepo.findByIdAndStatus(id, EntityStatus.ACTIVE).orElseThrow(
                () -> new NotFoundException("Department not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return departmentRepo.countAllByStatus(status);
    }

    @Override
    public long countAll() {
        return departmentRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepo.existsById(id);
    }

}
