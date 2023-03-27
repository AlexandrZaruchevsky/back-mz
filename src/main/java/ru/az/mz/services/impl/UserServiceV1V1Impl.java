package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.RoleDtoV1;
import ru.az.mz.dto.v1.UserAuthDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Role;
import ru.az.mz.model.User;
import ru.az.mz.repositories.UserRepo;
import ru.az.mz.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV1V1Impl implements UserServiceV1V1 {

    private final UserRepo userRepo;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    private final RoleServiceV1V1 roleServiceV1;

    public UserServiceV1V1Impl(UserRepo userRepo, SecurityService securityService, PasswordEncoder passwordEncoder, RoleServiceV1V1 roleServiceV1) {
        this.userRepo = userRepo;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.roleServiceV1 = roleServiceV1;
    }

    @Override
    @Cacheable(cacheNames = {"userSecurity"}, key = "#username")
    public User findByUsername(String username) throws MyException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findByUserNameForLogin(String username) throws MyException {
        return null;
    }

    @Override
    public Page<User> findAllByUsername(String username, Pageable pageable) {
        return userRepo.findAllByUsernameStartingWith(username, pageable);
    }

    @Override
    @CacheEvict(cacheNames = {"userSecurity"}, key = "#username")
    public void changePassword(String username, String password) throws MyException {
        User userFromDb = findByUsername(username);
        userFromDb.setPassword(passwordEncoder.encode(password));
        userRepo.save(userFromDb);
    }

    @Override
    @CacheEvict(cacheNames = {"userSecurity"}, allEntries = true)
    public void changeStatus(Long id) throws MyException {
        User userFromDb = findById(id);
        if(EntityStatus.ACTIVE.equals(userFromDb.getStatus())){
            userFromDb.setStatus(EntityStatus.NOT_ACTIVE);
        }else if (EntityStatus.NOT_ACTIVE.equals(userFromDb.getStatus())){
            userFromDb.setStatus(EntityStatus.ACTIVE);
        }else throw new MyException("Bad Status", HttpStatus.BAD_REQUEST);
        userRepo.save(userFromDb);
    }

    @Override
    @CacheEvict(cacheNames = {"userSecurity"}, allEntries = true)
    public User add(UserAuthDtoV1 userAuthDtoV1) {
        User user = new User();
        fillUser(userAuthDtoV1, user);
        user.setUsername(userAuthDtoV1.getUsername());
        user.setPassword(passwordEncoder.encode(userAuthDtoV1.getPassword()));
        return userRepo.save(user);
    }

    private void fillUser(UserAuthDtoV1 userAuthDtoV1, User user) {
        user.setEmail(userAuthDtoV1.getEmail());
        user.setLastName(userAuthDtoV1.getLastName());
        user.setFirstName(userAuthDtoV1.getFirstName());
        user.setMiddleName(userAuthDtoV1.getMiddleName());
        user.setSaveByUser(securityService.getUsername());
        List<Role> rolesFromDb = roleServiceV1.findAllByIds(userAuthDtoV1.getRoles().stream()
                .map(RoleDtoV1::getId)
                .collect(Collectors.toList()));
        user.setRoles(rolesFromDb);
    }

    @Override
    @CachePut(cacheNames = {"userSecurity"}, key = "#userAuthDtoV1.username")
    public User update(UserAuthDtoV1 userAuthDtoV1) throws MyException {
        User userFromDb = findById(userAuthDtoV1.getId());
        fillUser(userAuthDtoV1, userFromDb);
//        userFromDb.setStatus(EntityStatus.valueOf(userAuthDto.getStatus().toUpperCase()));
        return userRepo.save(userFromDb);
    }

    @Override
    @CacheEvict(cacheNames = {"userSecurity"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        User userFromDb = findById(id);
        userFromDb.setStatus(EntityStatus.DELETED);
        userFromDb.setSaveByUser(securityService.getUsername());
        userRepo.save(userFromDb);
        return true;
    }

    @Override
    public User findById(Long id) throws MyException {
        return userRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return userRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return userRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepo.existsById(id);
    }

}
