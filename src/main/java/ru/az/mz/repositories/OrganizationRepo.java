package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends PagingAndSortingRepository<Organization, Long> {

    Optional<Organization> findByIdAndStatus(Long id, EntityStatus status);

    Page<Organization> findAllByStatus(EntityStatus status, Pageable pageable);

    List<Organization> findAllByStatus(EntityStatus status);

    Page<Organization> findAllByShortNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    Page<Organization> findAllByInnStartingWithAndStatus(String inn, EntityStatus status, Pageable pageable);

    Page<Organization> findAllByOgrnStartingWithAndStatus(String ogrn, EntityStatus status, Pageable pageable);

    long countAllByStatus(EntityStatus status);

}
