package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.stats.DepStatDtoV1;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.services.*;
import ru.az.mz.util.UtilZ;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class EmployeeServiceV1Impl implements EmployeeServiceV1 {

    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;
    private final DepartmentServiceV1 departmentServiceV1;
    private final PositionServiceV1 positionServiceV1;
    private final PointOfPresenceServiceV1 pointOfPresenceServiceV1;

    public EmployeeServiceV1Impl(
            EmployeeRepo employeeRepo,
            SecurityService securityService,
            DepartmentServiceV1 departmentServiceV1,
            PositionServiceV1 positionServiceV1,
            PointOfPresenceServiceV1 pointOfPresenceServiceV1
    ) {
        this.employeeRepo = employeeRepo;
        this.securityService = securityService;
        this.departmentServiceV1 = departmentServiceV1;
        this.positionServiceV1 = positionServiceV1;
        this.pointOfPresenceServiceV1 = pointOfPresenceServiceV1;
    }

    @Override
    @Cacheable(cacheNames = {"employees"}, key = "{#fio, #pageable.pageSize, #pageable.pageNumber}")
    public Page<Employee> findByFIO(String fio, Pageable pageable) {
        String[] name = fio.trim().split("\\s+");
        String lastName = "";
        String firstName = "";
        String middleName = "";
        if (name.length >= 3) {
            lastName = name[0];
            firstName = name[1];
            middleName = name[2];
        } else if (name.length == 2) {
            lastName = name[0];
            firstName = name[1];
            middleName = "";
        } else if (name.length == 1) {
            lastName = name[0];
            firstName = "";
            middleName = "";
        }
        return name.length == 0
                ? employeeRepo.findAllByStatus(EntityStatus.ACTIVE, pageable)
                : employeeRepo.findAllByLastNameStartingWithAndFirstNameStartingWithAndMiddleNameStartingWithAndStatus(
                lastName,
                firstName,
                middleName,
                EntityStatus.ACTIVE,
                pageable);
    }

    @Override
    @Cacheable(cacheNames = {"employees"}, key = "{#kspd, #pageable.pageSize, #pageable.pageNumber}")
    public Page<Employee> findByKspd(String kspd, Pageable pageable) {
        return employeeRepo.findAllByKspdStartingWithAndStatus(kspd, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<EmployeeDtoV1> findAllByFioForChoice(String fio) {
        Map<String, String> fio1 = UtilZ.getFio(fio);
        return employeeRepo.findAllByLastNameStartingWithAndFirstNameStartingWithAndMiddleNameStartingWithAndStatus(
                fio1.get("lastName"),
                fio1.get("firstName"),
                fio1.get("middleName"),
                EntityStatus.ACTIVE,
                PageRequest.of(0, 10, Sort.by("lastName", "firstName", "middleName"))
        ).map(EmployeeDtoV1::createWithPositionAndDepartment)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployeeDtoV1> findAll(PageRequestDtoV1 pageRequest) {
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employeeList", "guest-employees", "empl-stats"}, allEntries = true)
    public Employee add(EmployeeDtoV1 employeeDtoV1) throws MyException {
        Employee employee = new Employee();
        fillEmployee(employeeDtoV1, employee);
        return employeeRepo.save(employee);
    }

    private void fillEmployee(EmployeeDtoV1 employeeDtoV1, Employee employee) throws MyException {
        employee.setLastName(employeeDtoV1.getLastName());
        employee.setFirstName(employeeDtoV1.getFirstName());
        employee.setMiddleName(employeeDtoV1.getMiddleName());
        employee.setEmail(employeeDtoV1.getEmail());
        employee.setPhone(employeeDtoV1.getPhone());
        employee.setKspd(employeeDtoV1.getKspd());
        employee.setAccountName(employeeDtoV1.getAccountName());
        employee.setPrincipalName(employeeDtoV1.getPrincipalName());
        employee.setDescription(employeeDtoV1.getDescription());
        employee.setInfo(employeeDtoV1.getWsName());
        employee.setSaveByUser(securityService.getUsername());
        employee.setDepartment(
                employeeDtoV1.getDepId() != null && employeeDtoV1.getDepId() > 0
                        ? departmentServiceV1.findById(employeeDtoV1.getDepId())
                        : null
        );
        employee.setPosition(
                employeeDtoV1.getPosId() != null && employeeDtoV1.getPosId() > 0
                        ? positionServiceV1.findById(employeeDtoV1.getPosId())
                        : null
        );
        employee.setPointOfPresence(
                employeeDtoV1.getPofId() != null && employeeDtoV1.getPofId() > 0
                        ? pointOfPresenceServiceV1.findById(employeeDtoV1.getPofId())
                        : null
        );
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employeeList", "guest-employees", "empl-stats"}, allEntries = true)
    public Employee update(EmployeeDtoV1 employeeDtoV1) throws MyException {
        Employee employee = findById(employeeDtoV1.getId());
        fillEmployee(employeeDtoV1, employee);
        return employeeRepo.save(employee);
    }

    @Override
    @CacheEvict(cacheNames = {"employees", "employeeList", "guest-employees","empl-stats"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        Employee employee = findById(id);
        employee.setStatus(EntityStatus.DELETED);
        employee.setSaveByUser(securityService.getUsername());
        employeeRepo.save(employee);
        return true;
    }

    @Override
    @Cacheable(cacheNames = {"employees"}, key = "#emplId")
    public Employee findById(Long emplId) throws MyException {
        return employeeRepo.findByIdAndStatus(emplId, EntityStatus.ACTIVE).orElseThrow(
                () -> new NotFoundException("Employee not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Cacheable(cacheNames = {"employees"}, key = "{#pageable.pageSize, #pageable.pageNumber}")
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Cacheable(cacheNames = {"employeeList"})
    public List<Employee> findAll() {
        return new ArrayList<>(employeeRepo.findAll());
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return employeeRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return employeeRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepo.existsById(id);
    }

}
