package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipType;

import java.util.List;
import java.util.Optional;

public interface EquipTypeRepo extends PagingAndSortingRepository<EquipType, Long> {

    Page<EquipType> findAllByStatus(EntityStatus status, Pageable pageable);

    Page<EquipType> findAllByNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    List<EquipType> findAllByStatus(EntityStatus status);

    List<EquipType> findAll();

    @EntityGraph("EquipType.withEquipModels")
    Optional<EquipType> findByIdAndStatus(Long id, EntityStatus status);

    long countByStatus(EntityStatus status);

}
