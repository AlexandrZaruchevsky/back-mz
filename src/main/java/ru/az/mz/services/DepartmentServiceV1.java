package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.DepartmentDtoV1;
import ru.az.mz.dto.v1.DepartmentDtoV2;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Department;

import java.util.List;

public interface DepartmentServiceV1 extends CrudServiceV1<Department, DepartmentDtoV2> {

    Page<Department> findAllByName(String name, Pageable pageable);

    Page<Department> findAll(PageRequestDtoV1 pageRequest) throws MyException;

    List<Department> findAllByOrgId(Long id) throws MyException;

}
