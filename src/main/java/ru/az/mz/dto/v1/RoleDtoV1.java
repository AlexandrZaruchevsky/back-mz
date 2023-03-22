package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class RoleDtoV1 {

    long id;
    String name;
    List<String> permissions;

    public static RoleDtoV1 create(Role role) {
        return new RoleDtoV1(
                role.getId(),
                role.getName(),
                Collections.emptyList()
        );
    }

    public static RoleDtoV1 createWithPermissions(Role role) {
        return new RoleDtoV1(
                role.getId(),
                role.getName(),
                role.getPermissions() == null
                        ? Collections.emptyList()
                        : role.getPermissions().stream()
                                .map(Enum::name)
                                .distinct()
                                .collect(Collectors.toList())
        );
    }

}
