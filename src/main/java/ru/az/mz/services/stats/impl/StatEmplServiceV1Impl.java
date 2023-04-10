package ru.az.mz.services.stats.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.stats.DepStatDtoV1;
import ru.az.mz.dto.v1.stats.EmplStatDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.services.stats.StatEmplServiceV1;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class StatEmplServiceV1Impl implements StatEmplServiceV1 {

    private final EmployeeRepo employeeRepo;

    public StatEmplServiceV1Impl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
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
        long amountNotActive = empls.stream()
                .filter(employee -> EntityStatus.NOT_ACTIVE.equals(employee.getStatus()))
                .count();
        long amountDeleted = empls.stream()
                .filter(employee -> EntityStatus.DELETED.equals(employee.getStatus()))
                .count();
        Map<Long, Long> countEmplByDep = empls.stream()
                .filter(employee -> employee.getDepartment()!=null)
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

}
