package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;

import java.util.Optional;

public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {

    @EntityGraph("Employee.withDepartmentAndPosition")
    Page<Employee> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Employee.withAll")
    Optional<Employee> findByIdAndStatus(Long id, EntityStatus status);

    @EntityGraph("Employee.withDepartmentAndPosition")
    Page<Employee> findAllByLastNameStartingWithAndFirstNameStartingWithAndMiddleNameStartingWithAndStatus(
            String lastName,
            String firstName,
            String middleName,
            EntityStatus status,
            Pageable pageable
    );

    @EntityGraph("Employee.withDepartmentAndPosition")
    Page<Employee> findAllByKspdStartingWithAndStatus(
            String kspd,
            EntityStatus status,
            Pageable pageable
    );

    long countByStatus(EntityStatus status);

}
