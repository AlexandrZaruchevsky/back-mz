package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;
import ru.az.mz.model.PointOfPresence;

import java.util.List;
import java.util.Optional;

public interface PointOfPresenceRepo extends PagingAndSortingRepository<PointOfPresence, Long> {

    @EntityGraph("PointOfPresence.withOrganization")
    Optional<PointOfPresence> findByIdAndStatus(Long id, EntityStatus status);

    @EntityGraph("PointOfPresence.withOrganization")
    Page<PointOfPresence> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("PointOfPresence.withOrganization")
    Page<PointOfPresence> findAllByShortNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("PointOfPresence.withOrganization")
    Page<PointOfPresence> findAllByShortNameContainingAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("PointOfPresence.withOrganization")
    List<PointOfPresence> findAllByOrganizationAndStatus(Organization organization, EntityStatus status);


    long countByStatus(EntityStatus status);

}
