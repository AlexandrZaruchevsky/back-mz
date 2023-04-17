package ru.az.mz.services;

import ru.az.mz.dto.v1.RoleDtoV1;
import ru.az.mz.model.Role;

import java.util.List;

public interface RoleServiceV1V1 extends CrudServiceV1<Role, RoleDtoV1> {

    List<Role> findAllByIds(List<Long> ids);

    List<RoleDtoV1> findAllRolesDto();

}
