package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.EquipDtoV1;
import ru.az.mz.dto.v1.EquipParentsDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.PageRequestEquipDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Equip;

import java.util.List;

public interface EquipServiceV1 extends CrudServiceV1<Equip, EquipDtoV1> {

    Page<Equip> findAllByName(String name, Pageable pageable);
    Page<EquipDtoV1> findAll(PageRequestEquipDtoV1 pageRequest) throws MyException;
    Page<Equip> findAllByInventoryNumber(String inventoryNumber, Pageable pageable);
    Page<Equip> findAllBySerialNumber(String serialNumber, Pageable pageable);
    List<Equip> findAllByStatus(EntityStatus status);

    List<EquipDtoV1> findAllChildren(Long parentId);
    EquipDtoV1 findChildById(Long id) throws MyException;

    EquipParentsDtoV1 getEquipParents();

}
