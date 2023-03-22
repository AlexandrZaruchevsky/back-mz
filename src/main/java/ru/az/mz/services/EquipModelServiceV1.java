package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EquipModelDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipModel;
import ru.az.mz.model.EquipType;

import java.util.List;

public interface EquipModelServiceV1 extends CrudServiceV1<EquipModel, EquipModelDtoV1> {

    Page<EquipModel> findAllByName(String name, Pageable pageable);

    List<EquipModel> findAll(EntityStatus status);

}
