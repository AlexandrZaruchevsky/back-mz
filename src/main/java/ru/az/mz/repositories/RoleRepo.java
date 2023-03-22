package ru.az.mz.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Role;

import java.util.List;

public interface RoleRepo extends PagingAndSortingRepository<Role, Long> {

    long countAllByStatus(EntityStatus status);

}
