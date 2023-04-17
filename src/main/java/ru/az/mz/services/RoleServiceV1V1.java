package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.RoleDtoV1;
import ru.az.mz.model.Role;

import java.util.List;

public interface RoleServiceV1V1 extends CrudServiceV1<Role, RoleDtoV1> {

    List<Role> findAllByIds(List<Long> ids);

    List<RoleDtoV1> findAllRolesDto();

    Page<RoleDtoV1> findAllByName(PageRequestDtoV1 pageRequest);

}
