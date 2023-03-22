package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EquipTypeDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipType;

import java.util.List;

public interface EquipTypeServiceV1 extends CrudServiceV1<EquipType, EquipTypeDtoV1>{

    Page<EquipType> findAllByName(String name, Pageable pageable);

    List<EquipType> findAll(EntityStatus status);

}
