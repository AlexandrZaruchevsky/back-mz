package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.*;

import java.util.List;
import java.util.Optional;

public interface EquipRepo extends PagingAndSortingRepository<Equip, Long> {

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildren(EntityStatus status, boolean isChildren, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndShortNameContainingOrShortNameIsNull(EntityStatus status, boolean isChildren, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, boolean isChildren, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, boolean isChildren, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndArmAndShortNameContainingOrShortNameIsNull(EntityStatus status, boolean isChildren, Arm arm, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndArmAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, boolean isChildren, Arm arm, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndArmAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, boolean isChildren, Arm arm, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipTypeAndShortNameContainingOrShortNameIsNull(EntityStatus status, boolean isChildren, EquipType equipType, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipTypeAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, boolean isChildren, EquipType equipType, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipTypeAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, boolean isChildren, EquipType equipType, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipModelAndShortNameContainingOrShortNameIsNull(EntityStatus status, boolean isChildren, EquipModel equipModel, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipModelAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, boolean isChildren, EquipModel equipModel, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndChildrenAndEquipModelAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, boolean isChildren, EquipModel equipModel, String name, Pageable pageable);

    Page<Equip> findAllByShortNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    Page<Equip> findAllByInventoryNumberStartingWithAndStatus(String inventoryNumber, EntityStatus status, Pageable pageable);

    Page<Equip> findAllBySerialNumberStartingWithAndStatus(String serialNumber, EntityStatus status, Pageable pageable);

    List<Equip> findAllByStatusAndChildren(EntityStatus status, boolean isChildren);

    List<Equip> findAll();

    @EntityGraph("Equip.withAll")
    Optional<Equip> findByIdAndStatus(Long id, EntityStatus status);

    long countByStatus(EntityStatus status);

    @EntityGraph("Equip.withEquipType")
    List<Equip> findAllByChildrenAndParentIdAndStatus(boolean isChildren, Long id, EntityStatus status);
    @EntityGraph("Equip.withAll")
    Optional<Equip> findByIdAndChildrenAndStatus(Long id, boolean isChildren, EntityStatus status);

}
