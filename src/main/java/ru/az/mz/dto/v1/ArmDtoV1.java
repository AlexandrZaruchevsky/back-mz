package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Arm;

import java.util.Collections;
import java.util.List;

@Value
public class ArmDtoV1 {

    Long id;
    String name;
    String office;
    Long popId;
    String popName;
    EmployeeDtoV1 boss;
    List<EquipDtoV1> equips;

    public static ArmDtoV1 create(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                null,
                null,
                null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithEmployee(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                null,
                null,
                arm.getEmployee() != null
                        ? EmployeeDtoV1.create(arm.getEmployee())
                        : null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithAll(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                null,
                null,
                null,
                Collections.emptyList()
        );
    }

}
