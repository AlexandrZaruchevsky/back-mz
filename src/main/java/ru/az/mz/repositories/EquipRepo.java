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
    Page<Equip> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndShortNameContainingOrShortNameIsNull(EntityStatus status, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndArmAndShortNameContainingOrShortNameIsNull(EntityStatus status, Arm arm, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndArmAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, Arm arm, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndArmAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, Arm arm, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipTypeAndShortNameContainingOrShortNameIsNull(EntityStatus status, EquipType equipType, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipTypeAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, EquipType equipType, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipTypeAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, EquipType equipType, String name, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipModelAndShortNameContainingOrShortNameIsNull(EntityStatus status, EquipModel equipModel, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipModelAndInventoryNumberContainingOrInventoryNumberIsNull(EntityStatus status, EquipModel equipModel, String name, Pageable pageable);
    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatusAndEquipModelAndSerialNumberContainingOrSerialNumberIsNull(EntityStatus status, EquipModel equipModel, String name, Pageable pageable);

    Page<Equip> findAllByShortNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    Page<Equip> findAllByInventoryNumberStartingWithAndStatus(String inventoryNumber, EntityStatus status, Pageable pageable);

    Page<Equip> findAllBySerialNumberStartingWithAndStatus(String serialNumber, EntityStatus status, Pageable pageable);

    List<Equip> findAllByStatus(EntityStatus status);

    List<Equip> findAll();

    @EntityGraph("Equip.withAll")
    Optional<Equip> findByIdAndStatus(Long id, EntityStatus status);

    long countByStatus(EntityStatus status);

}
