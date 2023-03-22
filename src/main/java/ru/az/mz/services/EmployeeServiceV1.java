package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.model.Employee;

public interface EmployeeServiceV1 extends CrudServiceV1<Employee, EmployeeDtoV1> {

    Page<Employee> findByFIO(String fio, Pageable pageable);

    Page<Employee> findByKspd(String kspd, Pageable pageable);

}
