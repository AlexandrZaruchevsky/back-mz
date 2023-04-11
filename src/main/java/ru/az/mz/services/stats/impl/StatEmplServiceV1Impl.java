package ru.az.mz.services.stats.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.stats.ArmInfoV1;
import ru.az.mz.dto.v1.stats.DepStatDtoV1;
import ru.az.mz.dto.v1.stats.EmplInfoDtoV1;
import ru.az.mz.dto.v1.stats.EmplStatDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.ArmRepo;
import ru.az.mz.repositories.DepartmentRepo;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.services.stats.StatEmplServiceV1;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class StatEmplServiceV1Impl implements StatEmplServiceV1 {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final ArmRepo armRepo;

    public StatEmplServiceV1Impl(
            EmployeeRepo employeeRepo,
            DepartmentRepo departmentRepo,
            ArmRepo armRepo
    ) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.armRepo = armRepo;
    }

    @Override
    @Cacheable(cacheNames = {"empl-stats"})
    public EmplStatDtoV1 getStatistics() {
        List<Employee> empls = employeeRepo.findAll();
        List<DepStatDtoV1> depStat = empls.stream()
                .map(Employee::getDepartment)
                .filter(Objects::nonNull)
                .distinct()
                .map(DepStatDtoV1::create)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        long total = empls.size();
        long amountActive = empls.stream()
                .filter(employee -> EntityStatus.ACTIVE.equals(employee.getStatus()))
                .count();
        total = amountActive;
        long amountNotActive = empls.stream()
                .filter(employee -> EntityStatus.NOT_ACTIVE.equals(employee.getStatus()))
                .count();
        long amountDeleted = empls.stream()
                .filter(employee -> EntityStatus.DELETED.equals(employee.getStatus()))
                .count();
        Map<Long, Long> countEmplByDep = empls.stream()
                .filter(employee -> employee.getDepartment() != null)
                .map(EmployeeDtoV1::createWithPositionAndDepartment)
                .collect(Collectors.groupingBy(EmployeeDtoV1::getDepId, Collectors.counting()));
        List<DepStatDtoV1> stats = depStat.stream()
                .map(depStatDtoV1 -> new DepStatDtoV1(
                        depStatDtoV1.getDepId(),
                        depStatDtoV1.getDepName(),
                        countEmplByDep.get(depStatDtoV1.getDepId()))
                )
                .sorted(Comparator.comparing(DepStatDtoV1::getDepName))
                .collect(Collectors.toList());
        return new EmplStatDtoV1(
                total,
                amountActive,
                amountNotActive,
                amountDeleted,
                stats
        );
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = {"empl-by-dep-stats"})
    public List<EmployeeDtoV1> findAllByDep(Long depId) {
        List<EmployeeDtoV1> empls = new ArrayList<>();
        if (depId > 0) {
            departmentRepo.findByIdAndStatus(depId, EntityStatus.ACTIVE)
                    .ifPresent(department ->
                            empls.addAll(
                                    employeeRepo.findAllByDepartmentAndStatus(department, EntityStatus.ACTIVE).stream()
                                            .sorted(
                                                    Comparator.comparing(Employee::getLastName)
                                                            .thenComparing(Employee::getFirstName)
                                                            .thenComparing(Employee::getMiddleName)
                                            )
                                            .map(EmployeeDtoV1::createWithPositionAndDepartment)
                                            .collect(Collectors.toList())
                            )
                    );
        } else {
            empls.addAll(
                    employeeRepo.findAll().stream()
                            .filter(employee -> EntityStatus.ACTIVE.equals(employee.getStatus()))
                            .sorted(
                                    Comparator.comparing(Employee::getLastName)
                                            .thenComparing(Employee::getFirstName)
                                            .thenComparing(Employee::getMiddleName)
                            )
                            .map(EmployeeDtoV1::createWithPositionAndDepartment)
                            .collect(Collectors.toList())
            );
        }
        return empls;
    }

    @Override
    @Transactional
    public EmplInfoDtoV1 getEmplInfoById(Long emplId) {
        EmplInfoDtoV1 emplInfo = new EmplInfoDtoV1();
        employeeRepo.findByIdAndStatus(emplId, EntityStatus.ACTIVE)
                .ifPresent(employee -> {
                    emplInfo.setEmplId(employee.getId());
                    emplInfo.setEmployee(EmployeeDtoV1.createWithAll(employee));
                    emplInfo.setArmInfos(
                            armRepo.findAllByMolAndStatus(employee.getAccountName(), EntityStatus.ACTIVE).stream()
                                    .map(ArmInfoV1::create)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList())
                    );
                });
        return emplInfo;
    }

    @Override
    public Optional<EmplInfoDtoV1> getEmplInfoByUsername(String username) {
        if (username == null || "".equalsIgnoreCase(username.trim())) return Optional.empty();
        EmplInfoDtoV1 emplInfoDtoV1 = new EmplInfoDtoV1();
        emplInfoDtoV1.setArmInfos(
                armRepo.findAllByMolAndStatus(username.trim(), EntityStatus.ACTIVE).stream()
                        .map(ArmInfoV1::create)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList())
        );
        return Optional.of(emplInfoDtoV1);
    }

}
