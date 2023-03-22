package ru.az.mz.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.az.mz.model.Permission;
import ru.az.mz.model.Role;
import ru.az.mz.model.User;
import ru.az.mz.repositories.RoleRepo;
import ru.az.mz.repositories.UserRepo;
import ru.az.mz.services.AppInitService;

import java.util.List;
import java.util.Set;

@Service
public class AppInitServiceImpl implements AppInitService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.user.username}")
    private String username;
    @Value("${security.user.password}")
    private String password;
    @Value("${security.user.role}")
    private String role;
    @Value("${security.user.email}")
    private String email;


    public AppInitServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init() {
        Role role = new Role();
        role.setName(this.role);
        role.setSaveByUser(username);
        role.setPermissions(Set.of(Permission.values()));
        roleRepo.save(role);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setSaveByUser(username);
        user.setEmail(email);
        user.setRoles(List.of(role));
        userRepo.save(user);
    }

    @Override
    public long count() {
        return userRepo.count();
    }


}
