package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Equip;

import java.util.List;
import java.util.Optional;

public interface EquipRepo extends PagingAndSortingRepository<Equip, Long> {

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByShortNameContainingAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllByInventoryNumberContainingAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("Equip.withEquipType")
    Page<Equip> findAllBySerialNumberContainingAndStatus(String name, EntityStatus status, Pageable pageable);

    Page<Equip> findAllByShortNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    Page<Equip> findAllByInventoryNumberStartingWithAndStatus(String inventoryNumber, EntityStatus status, Pageable pageable);

    Page<Equip> findAllBySerialNumberStartingWithAndStatus(String serialNumber, EntityStatus status, Pageable pageable);

    List<Equip> findAllByStatus(EntityStatus status);

    List<Equip> findAll();

    @EntityGraph("Equip.withAll")
    Optional<Equip> findByIdAndStatus(Long id, EntityStatus status);

    long countByStatus(EntityStatus status);

}
