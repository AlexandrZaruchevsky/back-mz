package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.User;

import java.util.Optional;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    @EntityGraph("User.withAll")
    Optional<User> findByUsername(String username);

    @EntityGraph("User.withAll")
    Optional<User> findByIdAndStatus(Long id, EntityStatus status);

    Page<User> findAllByStatus(EntityStatus status, Pageable pageable);

    Page<User> findAllByUsernameStartingWith(String username, Pageable pageable);

    Page<User> findAllByUsernameContainingAndStatus(String username, EntityStatus status, Pageable pageable);
    Page<User> findAllByLastNameContainingAndFirstNameContainingAndMiddleNameContainingAndStatus(
            String lastName,
            String firstName,
            String middleName,
            EntityStatus status,
            Pageable pageable
    );

    long countByStatus(EntityStatus status);

}
