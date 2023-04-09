package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Employee;

import java.util.List;

public interface EmployeeServiceV1 extends CrudServiceV1<Employee, EmployeeDtoV1> {

    Page<Employee> findByFIO(String fio, Pageable pageable);

    Page<Employee> findByKspd(String kspd, Pageable pageable);

    List<EmployeeDtoV1> findAllByFioForChoice(String fio);

    Page<EmployeeDtoV1> findAll(PageRequestDtoV1 pageRequest);

}
