package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;
import ru.az.mz.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepo extends PagingAndSortingRepository<Position, Long> {

    @EntityGraph("Position.withAll")
    Optional<Position> findByIdAndStatus(Long id, EntityStatus status);

    @EntityGraph("Position.withOrganization")
    Page<Position> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Position.withOrganization")
    Page<Position> findAllByNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("Position.withOrganization")
    List<Position> findAllByOrganizationAndStatus(Organization organization, EntityStatus status);

    long countByStatus(EntityStatus status);

}
