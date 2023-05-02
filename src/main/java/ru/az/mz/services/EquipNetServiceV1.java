package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EquipNetDtoV1;
import ru.az.mz.dto.v1.projections.EquipNetDtoProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipNet;

import java.util.List;

public interface EquipNetServiceV1 extends CrudServiceV1<EquipNet, EquipNetDtoV1> {

    List<EquipNetDtoProjectionV1> getEquipNetList(Long id);

    EquipNetDtoProjectionV1 findByIdAsDto(Long id) throws MyException;

    Page<EquipNetDtoProjectionV1> findAll(EntityStatus status, Pageable pageable);

}
