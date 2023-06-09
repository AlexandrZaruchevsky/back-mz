package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Role;

import java.util.List;

public interface RoleRepo extends PagingAndSortingRepository<Role, Long> {

    long countAllByStatus(EntityStatus status);

    List<Role> findAllByStatus(EntityStatus status);

    Page<Role> findAllByStatus(EntityStatus status, Pageable pageable);
    Page<Role> findAllByNameContainingAndStatus(String name, EntityStatus status, Pageable pageable);

}
