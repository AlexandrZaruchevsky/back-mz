package ru.az.mz.services;

import ru.az.mz.dto.v1.EquipNetDtoV1;
import ru.az.mz.dto.v1.projections.EquipNetDtoProjectionV1;
import ru.az.mz.model.EquipNet;

import java.util.List;

public interface EquipNetServiceV1 extends CrudServiceV1<EquipNet, EquipNetDtoV1> {

    List<EquipNetDtoProjectionV1> getEquipNetList(Long id);

    EquipNetDtoProjectionV1 findByIdAsDto(Long id) throws MyException;


}
