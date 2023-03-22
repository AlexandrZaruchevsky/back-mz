package ru.az.mz.services;

/*
    T - object,
    K - DTO object
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.model.EntityStatus;

import java.util.List;

public interface CrudServiceV1<T, K> {

    T add(K k) throws MyException;
    T update(K k) throws MyException;
    boolean delete(long id) throws MyException;
    T findById(Long id) throws MyException;
    Page<T> findAll(Pageable pageable);
    List<T> findAll();
    long countByStatus(EntityStatus status);
    long countAll();
    boolean existsById(Long id);

}
