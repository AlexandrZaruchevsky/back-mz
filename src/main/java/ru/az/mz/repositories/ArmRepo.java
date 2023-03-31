package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.Arm;
import ru.az.mz.model.EntityStatus;

import java.util.List;
import java.util.Optional;

public interface ArmRepo extends PagingAndSortingRepository<Arm, Long> {

    @EntityGraph("Arm.withEmployee")
    Page<Arm> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Arm.withEmployee")
    Page<Arm> findAllByNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("Arm.withAll")
    Optional<Arm> findByIdAndStatus(Long id, EntityStatus status);


    List<Arm> findAllByStatus(EntityStatus status);

    long countByStatus(EntityStatus status);

}
