package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.OrganizationDtoV1;
import ru.az.mz.model.*;
import ru.az.mz.repositories.*;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.OrganizationServiceV1;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceV1Impl implements OrganizationServiceV1 {

    private final OrganizationRepo organizationRepo;
    private final PointOfPresenceRepo pointOfPresenceRepo;
    private final DepartmentRepo departmentRepo;
    private final PositionRepo positionRepo;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;

    public OrganizationServiceV1Impl(
            OrganizationRepo organizationRepo,
            PointOfPresenceRepo pointOfPresenceRepo,
            DepartmentRepo departmentRepo,
            PositionRepo positionRepo,
            EmployeeRepo employeeRepo,
            SecurityService securityService
    ) {
        this.organizationRepo = organizationRepo;
        this.pointOfPresenceRepo = pointOfPresenceRepo;
        this.departmentRepo = departmentRepo;
        this.positionRepo = positionRepo;
        this.employeeRepo = employeeRepo;
        this.securityService = securityService;
    }

    @Override
    public Page<Organization> findAllByName(String name, Pageable pageable) {
        return organizationRepo.findAllByShortNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Organization> findAllByInn(String inn, Pageable pageable) {
        return organizationRepo.findAllByInnStartingWithAndStatus(inn, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Organization> findAllByOgrn(String ogrn, Pageable pageable) {
        return organizationRepo.findAllByOgrnStartingWithAndStatus(ogrn, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = {"organizationWithDependencies"}, key = "#orgId")
    public OrganizationDtoV1 findByIdWithAllDependencies(Long orgId) throws MyException {
        Organization organization = organizationRepo.findByIdAndStatus(orgId, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        return OrganizationDtoV1.createWithAll(
                organization,
                pointOfPresenceRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(PointOfPresence::getShortName)).collect(Collectors.toList()),
                departmentRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(Department::getName)).collect(Collectors.toList()),
                positionRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(Position::getName)).collect(Collectors.toList()),
                employeeRepo.findByIdAndStatus(organization.getBossId(), EntityStatus.ACTIVE)
                        .orElse(null)
        );
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "organizationWithEmployees", key = "#orgId")
    public OrganizationDtoV1 findByIdWithAllEmployees(Long orgId) throws MyException {
        Organization orgFromDb = organizationRepo.findByIdAndStatus(orgId, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        List<Employee> employees = departmentRepo.findAllByOrOrganizationAndStatus(orgFromDb, EntityStatus.ACTIVE).stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.toList());
        return OrganizationDtoV1.createWithEmployees(orgFromDb,employees);
    }

    @Override
    public OrganizationDtoV1 findByIdWithAllDepartments(Long orgId) throws MyException {
        throw new MyException("Method findByIdWithAllDepartments not implemented", HttpStatus.BAD_REQUEST);
    }

    private void fillOrganization(OrganizationDtoV1 organizationDtoV1, Organization organization) {
        organization.setShortName(organizationDtoV1.getShortName());
        organization.setFullName(organizationDtoV1.getFullName());
        organization.setInn(organizationDtoV1.getInn());
        organization.setKpp(organizationDtoV1.getKpp());
        organization.setOgrn(organizationDtoV1.getOgrn());
        organization.setBossId(organizationDtoV1.getBossId());
        organization.setSaveByUser(securityService.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList" ,"organizationWithEmployees"}, allEntries = true)
    public Organization add(OrganizationDtoV1 organizationDtoV1) {
        Organization organization = new Organization();
        fillOrganization(organizationDtoV1, organization);
        return organizationRepo.save(organization);
    }

    @Override
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public Organization update(OrganizationDtoV1 organizationDtoV1) throws MyException {
        Organization organization = findById(organizationDtoV1.getId());
        fillOrganization(organizationDtoV1, organization);
        return organizationRepo.save(organization);
    }

    @Override
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public boolean delete(long orgId) throws MyException {
        Organization organization = findById(orgId);
        organization.setStatus(EntityStatus.DELETED);
        organization.setSaveByUser(securityService.getUsername());
        organizationRepo.save(organization);
        return true;
    }

    @Override
    public Organization findById(Long id) throws MyException {
        return organizationRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Cacheable(cacheNames = {"orgList"})
    public List<Organization> findAll() {
        return organizationRepo.findAllByStatus(EntityStatus.ACTIVE);
    }

    //    @Override
//    public List<Organization> findAll() {
//        List<Organization> organizations = new ArrayList<>();
//        organizationRepo.findAll().forEach(organizations::add);
//        return organizations;
//    }
//
    @Override
    public long countByStatus(EntityStatus status) {
        return organizationRepo.countAllByStatus(status);
    }

    @Override
    public long countAll() {
        return organizationRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return organizationRepo.existsById(id);
    }

}
