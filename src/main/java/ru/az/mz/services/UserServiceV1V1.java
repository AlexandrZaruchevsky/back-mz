package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.UserAuthDtoV1;
import ru.az.mz.model.User;

public interface UserServiceV1V1 extends CrudServiceV1<User, UserAuthDtoV1> {

    User findByUsername(String username) throws MyException;
    User findByUserNameForLogin(String username) throws MyException;

    Page<User> findAllByUsername(String username, Pageable pageable);

    void changePassword(String username, String password) throws MyException;

    void changeStatus(Long id) throws MyException;

}
