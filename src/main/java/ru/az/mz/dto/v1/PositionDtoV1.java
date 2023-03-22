package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Position;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PositionDtoV1 {

    Long id;
    String name;
    Long orgId;
    String orgName;
    List<EmployeeDtoV1> employees;

    private static PositionDtoV1 createDefault(Position position, List<EmployeeDtoV1> employees) {
        return new PositionDtoV1(
                position.getId(),
                position.getName(),
                position.getOrganization() != null ? position.getOrganization().getId() : -1,
                position.getOrganization() != null ? position.getOrganization().getShortName() : "",
                employees
        );
    }

    public static PositionDtoV1 create(Position position) {
        return createDefault(position, Collections.emptyList());
    }

    public static PositionDtoV1 createWithEmployees(Position position) {
        return createDefault(
                position,
                position.getEmployees() == null
                        ? Collections.emptyList()
                        : position.getEmployees().stream()
                        .map(EmployeeDtoV1::create)
                        .collect(Collectors.toList())
        );
    }

}
