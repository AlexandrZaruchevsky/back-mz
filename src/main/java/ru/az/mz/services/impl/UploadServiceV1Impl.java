package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.model.*;
import ru.az.mz.repositories.*;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;
import ru.az.mz.services.UploadServiceV1;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UploadServiceV1Impl implements UploadServiceV1 {

    private final DepartmentRepo departmentRepo;
    private final OrganizationRepo organizationRepo;
    private final PositionRepo positionRepo;
    private final PointOfPresenceRepo pointOfPresenceRepo;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;

    public UploadServiceV1Impl(
            DepartmentRepo departmentRepo,
            OrganizationRepo organizationRepo,
            PositionRepo positionRepo,
            PointOfPresenceRepo pointOfPresenceRepo,
            EmployeeRepo employeeRepo,
            SecurityService securityService
    ) {
        this.departmentRepo = departmentRepo;
        this.organizationRepo = organizationRepo;
        this.positionRepo = positionRepo;
        this.pointOfPresenceRepo = pointOfPresenceRepo;
        this.employeeRepo = employeeRepo;
        this.securityService = securityService;
    }


    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public void loadTopDeps(List<ADUser> adUsers) throws MyException {
        Organization orgFromDb = organizationRepo.findById(1L).orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        List<Department> depList = adUsers.stream()
                .map(ADUser::getCompany)
                .filter(Objects::nonNull)
                .map(String::trim)
                .distinct()
                .map(topDep -> {
                    Department department = new Department();
                    department.setOrganization(orgFromDb);
                    department.setName(topDep);
                    department.setTopLevel(true);
                    department.setSaveByUser(securityService.getUsername());
                    return department;
                })
                .collect(Collectors.toList());
        departmentRepo.saveAll(depList);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public void loadDeps(List<ADUser> adUsers) throws MyException {
        Organization orgFromDb = organizationRepo.findById(1L).orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        List<Department> topDeps = departmentRepo.findAllByTopLevelAndStatus(true, EntityStatus.ACTIVE);
        topDeps.forEach(department -> {
            adUsers.stream()
                    .filter(adUser -> adUser.getCompany() != null && department.getName().trim().equalsIgnoreCase(adUser.getCompany().trim()))
                    .map(ADUser::getDepartment)
                    .distinct()
                    .filter(depName -> !(depName != null && department.getName().trim().equalsIgnoreCase(depName.trim())))
                    .map(depName -> {
                        Department dep = new Department();
                        dep.setOrganization(orgFromDb);
                        dep.setName(depName);
                        dep.setTopLevel(false);
                        dep.setParentId(department.getId());
                        dep.setSaveByUser(securityService.getUsername());
                        return dep;
                    })
                    .forEach(departmentRepo::save);
        });
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public void loadPositions(List<ADUser> adUsers) throws MyException {
        Organization orgFromDb = organizationRepo.findById(1L).orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        adUsers.stream()
                .map(ADUser::getTitle)
                .filter(Objects::nonNull)
//                .map(s -> s.replaceAll("\\s+"," "))
                .map(String::trim)
                .distinct()
                .map(posName -> {
                    Position position = new Position();
                    position.setName(posName);
                    position.setOrganization(orgFromDb);
                    position.setSaveByUser(securityService.getUsername());
                    return position;
                })
                .forEach(positionRepo::save);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public void loadPointOfPresences(List<ADUser> adUsers) throws MyException {
        Organization orgFromDb = organizationRepo.findById(1L).orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        adUsers.stream()
                .map(ADUser::getCity)
                .filter(Objects::nonNull)
                .map(String::trim)
                .distinct()
                .map(pofName -> {
                    PointOfPresence pointOfPresence = new PointOfPresence();
                    pointOfPresence.setShortName(pofName);
                    pointOfPresence.setOrganization(orgFromDb);
                    pointOfPresence.setSaveByUser(securityService.getUsername());
                    return pointOfPresence;
                })
                .forEach(pointOfPresenceRepo::save);
    }


    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies", "orgList", "organizationWithEmployees"}, allEntries = true)
    public void loadEmployees(List<ADUser> adUsers) throws MyException {
        Organization orgFromDb = organizationRepo.findById(1L).orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
        List<Department> deps = departmentRepo.findAllByOrganizationAndStatus(orgFromDb, EntityStatus.ACTIVE);
        List<Position> positions = positionRepo.findAllByOrganizationAndStatus(orgFromDb, EntityStatus.ACTIVE);
        List<PointOfPresence> pofs = pointOfPresenceRepo.findAllByOrganizationAndStatus(orgFromDb, EntityStatus.ACTIVE);
        adUsers.stream()
                .map(adUser -> {
                    Employee employee = new Employee();
                    employee.setLastName(adUser.getSurname().trim());
                    employee.setFirstName(adUser.getGivenName() != null ? adUser.getGivenName().trim() : null);
                    employee.setMiddleName(adUser.getMiddleName() != null ? adUser.getMiddleName().trim() : null);
                    employee.setEmail(adUser.getMail());
                    employee.setPhone(adUser.getMobile());
                    employee.setKspd(adUser.getOfficePhone());
                    employee.setAccountName(adUser.getSamAccountName());
                    employee.setPrincipalName(adUser.getUserPrincipalName());
                    employee.setDescription(adUser.getStreetAddress());
                    employee.setInfo(getWorkStation(adUser.getInfo()));
                    deps.stream()
                            .filter(department -> adUser.getDepartment() != null && department.getName().trim().equalsIgnoreCase(adUser.getDepartment().trim()))
                            .findFirst()
                            .ifPresent(employee::setDepartment);
                    positions.stream()
                            .filter(position -> adUser.getTitle() != null && position.getName().trim().equalsIgnoreCase(adUser.getTitle().trim()))
                            .findFirst()
                            .ifPresent(employee::setPosition);
                    pofs.stream()
                            .filter(pof -> adUser.getCity() != null && pof.getShortName().trim().equalsIgnoreCase(adUser.getCity().trim()))
                            .findFirst()
                            .ifPresent(employee::setPointOfPresence);
                    employee.setSaveByUser(securityService.getUsername());
                    return employee;
                })
                .forEach(employeeRepo::save);
    }

    @Override
    @Transactional
    @CacheEvict(
            cacheNames = {
                    "organizationWithDependencies",
                    "orgList",
                    "organizationWithEmployees",
                    "employees",
                    "employeeList"
            },
            allEntries = true
    )
    public void clearEmployeesWithDependencies() {
        employeeRepo.deleteAll();
        positionRepo.deleteAll();
        pointOfPresenceRepo.deleteAll();
        departmentRepo.deleteAll();
    }

    private String getWorkStation(String info) {
        String[] strings = info != null ? info.split(":") : null;
        return strings != null && strings.length > 1 ? strings[1].trim() : null;
    }

}
