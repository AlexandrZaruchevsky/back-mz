package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.ArmDtoV1;
import ru.az.mz.model.Arm;

public interface ArmServiceV1 extends CrudServiceV1<Arm, ArmDtoV1> {

    Page<Arm> findByName(String name, Pageable pageable);

}
