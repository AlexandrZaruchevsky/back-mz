package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.ArmDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Arm;

import java.util.List;

public interface ArmServiceV1 extends CrudServiceV1<Arm, ArmDtoV1> {

    Page<Arm> findByName(String name, Pageable pageable);

    Page<ArmDtoV1> findAll(PageRequestDtoV1 pageRequest);

    List<ArmDtoV1> findAllByNameForChoice(String name);

}
