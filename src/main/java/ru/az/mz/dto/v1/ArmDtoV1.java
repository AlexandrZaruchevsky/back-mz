package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Arm;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ArmDtoV1 {

    Long id;
    String name;
    String office;
    PointOfPresenceDtoV1 pointOfPresence;
    EmployeeDtoV1 employee;
    List<EquipDtoV1> equips;

    private static ArmDtoV1 createDefault(
            Arm arm,
            PointOfPresenceDtoV1 pointOfPresence,
            EmployeeDtoV1 employee,
            List<EquipDtoV1> equips
    ) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                pointOfPresence,
                employee,
                equips
        );
    }

    public static ArmDtoV1 create(Arm arm) {
        return createDefault(
                arm,
                null,
                null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithPointOfPresence(Arm arm) {
        return createDefault(
                arm,
                arm.getPointOfPresence() != null
                        ? PointOfPresenceDtoV1.create(arm.getPointOfPresence())
                        : null,
                null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithEmployee(Arm arm) {
        return createDefault(
                arm,
                null,
                arm.getEmployee() != null
                        ? EmployeeDtoV1.create(arm.getEmployee())
                        : null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithPointOfPresenceAndEmployee(Arm arm) {
        return createDefault(
                arm,
                arm.getPointOfPresence() != null
                        ? PointOfPresenceDtoV1.create(arm.getPointOfPresence())
                        : null,
                arm.getEmployee() != null
                        ? EmployeeDtoV1.create(arm.getEmployee())
                        : null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithAll(Arm arm) {
        return createDefault(
                arm,
                arm.getPointOfPresence() != null
                        ? PointOfPresenceDtoV1.create(arm.getPointOfPresence())
                        : null,
                arm.getEmployee() != null
                        ? EmployeeDtoV1.create(arm.getEmployee())
                        : null,
                arm.getEquips() != null
                        ? arm.getEquips().stream().map(EquipDtoV1::create).collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }

}
