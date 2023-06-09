package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipModel;
import ru.az.mz.model.EquipType;

import java.util.List;
import java.util.Optional;

public interface EquipModelRepo extends PagingAndSortingRepository<EquipModel, Long> {

    @EntityGraph("EquipModel.withEquipType")
    Page<EquipModel> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("EquipModel.withEquipType")
    Page<EquipModel> findAllByNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("EquipModel.withEquipType")
    Page<EquipModel> findAllByEquipTypeAndNameContainingAndStatus(EquipType equipType, String name, EntityStatus status, Pageable pageable);

    @EntityGraph("EquipModel.withEquipType")
    List<EquipModel> findAllByStatus(EntityStatus status);

    List<EquipModel> findAll();

    @EntityGraph("EquipModel.withAll")
    Optional<EquipModel> findByIdAndStatus(Long id, EntityStatus status);

    long countByStatus(EntityStatus status);

}
