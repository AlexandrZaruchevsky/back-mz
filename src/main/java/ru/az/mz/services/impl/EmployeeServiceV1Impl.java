package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
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
    public Page<Employee> findByKspd(String kspd, Pageable pageable) {
        return employeeRepo.findAllByKspdStartingWithAndStatus(kspd, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
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
    public Employee update(EmployeeDtoV1 employeeDtoV1) throws MyException {
        Employee employee = findById(employeeDtoV1.getId());
        fillEmployee(employeeDtoV1, employee);
        return employeeRepo.save(employee);
    }

    @Override
    public boolean delete(long id) throws MyException {
        Employee employee = findById(id);
        employee.setStatus(EntityStatus.DELETED);
        employee.setSaveByUser(securityService.getUsername());
        employeeRepo.save(employee);
        return true;
    }

    @Override
    public Employee findById(Long id) throws MyException {
        return employeeRepo.findByIdAndStatus(id, EntityStatus.ACTIVE).orElseThrow(
                () -> new NotFoundException("Employee not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(employees::add);
        return employees;
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
