package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.RoleDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Permission;
import ru.az.mz.model.Role;
import ru.az.mz.repositories.RoleRepo;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.RoleServiceV1V1;
import ru.az.mz.services.SecurityService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceV1V1Impl implements RoleServiceV1V1 {

    private final RoleRepo roleRepo;
    private final SecurityService securityService;

    public RoleServiceV1V1Impl(RoleRepo roleRepo, SecurityService securityService) {
        this.roleRepo = roleRepo;
        this.securityService = securityService;
    }

    @Override
    public List<Role> findAllByIds(List<Long> ids) {
        Iterable<Role> allById = roleRepo.findAllById(ids);
        List<Role> listRole = new ArrayList<>();
        allById.forEach(listRole::add);
        return listRole;
    }

    @Override
    public List<RoleDtoV1> findAllRolesDto() {
        return roleRepo.findAllByStatus(EntityStatus.ACTIVE).stream()
                .map(RoleDtoV1::create)
                .sorted(Comparator.comparing(RoleDtoV1::getName))
                .collect(Collectors.toList());
    }

    private void fillRole(RoleDtoV1 roleDtoV1, Role role) {
        role.setName(roleDtoV1.getName());
        Set<Permission> permissions = roleDtoV1.getPermissions() == null
                ? Collections.singleton(Permission.GUEST)
                : roleDtoV1.getPermissions().stream()
                .map(Permission::valueOf)
//                .map(p -> Permission.valueOf(p.getPermission().toUpperCase()))
                .collect(Collectors.toSet());
        role.setPermissions(permissions);
        role.setSaveByUser(securityService.getUsername());
    }

    @Override
    public Role add(RoleDtoV1 roleDtoV1) {
        Role role = new Role();
        fillRole(roleDtoV1, role);
        return roleRepo.save(role);
    }

    @Override
    public Role update(RoleDtoV1 roleDtoV1) throws MyException {
        Role role = findById(roleDtoV1.getId());
        fillRole(roleDtoV1, role);
        return roleRepo.save(role);
    }

    @Override
    public boolean delete(long id) throws MyException {
        Role role = findById(id);
        role.setStatus(EntityStatus.DELETED);
        role.setSaveByUser(securityService.getUsername());
        roleRepo.save(role);
        return true;
    }

    @Override
    public Role findById(Long id) throws MyException {
        return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Role not found for id - " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return roleRepo.countAllByStatus(status);
    }

    @Override
    public long countAll() {
        return roleRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepo.existsById(id);
    }

}
