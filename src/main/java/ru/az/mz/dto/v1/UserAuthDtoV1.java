package ru.az.mz.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.az.mz.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class UserAuthDtoV1 {

    long id;
    String username;
    String password;
    String email;
    String lastName;
    String firstName;
    String middleName;
    @JsonProperty("roles")
    List<RoleDtoV1> roles;
    String status;
    LocalDateTime created;
    LocalDateTime updated;

    private static UserAuthDtoV1 createDefault(User user, List<RoleDtoV1> roles){
        return new UserAuthDtoV1(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                roles,
                user.getStatus().name(),
                user.getCreated(),
                user.getUpdated()
        );
    }

    public static UserAuthDtoV1 create(User user) {
        return createDefault(user, Collections.emptyList());
    }

    public static UserAuthDtoV1 createWithRole(User user) {
        return createDefault(
                user,
                user.getRoles() == null
                        ? Collections.emptyList()
                            : user.getRoles().stream()
                                .map(RoleDtoV1::create)
                                .distinct()
                                .collect(Collectors.toList())
                );
    }

    public static UserAuthDtoV1 createWithRoleAndPermissions(User user) {
        return createDefault(
                user,
                user.getRoles() == null
                        ? Collections.emptyList()
                            : user.getRoles().stream()
                                .map(RoleDtoV1::createWithPermissions)
                                .collect(Collectors.toList())
        );
    }

}
