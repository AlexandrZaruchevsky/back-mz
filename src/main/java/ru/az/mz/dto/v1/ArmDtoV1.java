package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Arm;
import ru.az.mz.model.EntityStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ArmDtoV1 {

    Long id;
    String name;
    String office;
    String mol;
    String molFio;
    String description;
    Long popId;
    String popName;
    List<ArmDetailDtoV1> armDetails;

    public static ArmDtoV1 create(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                arm.getMol(),
                arm.getMolFio(),
                arm.getDescription(),
                -1L,
                null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithPop(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                arm.getMol(),
                arm.getMolFio(),
                arm.getDescription(),
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getId()
                        : -1L,
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getShortName()
                        : null,
                Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithAll(Arm arm) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                arm.getMol(),
                arm.getMolFio(),
                arm.getDescription(),
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getId()
                        : -1L,
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getShortName()
                        : null,
                arm.getArmDetails() != null
                        ? arm.getArmDetails().stream()
                            .filter(armDetail -> EntityStatus.ACTIVE.equals(armDetail.getStatus()))
                            .map(ArmDetailDtoV1::create).collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }

    public static ArmDtoV1 createWithAll(Arm arm, List<ArmDetailDtoV1> armDetails) {
        return new ArmDtoV1(
                arm.getId(),
                arm.getName(),
                arm.getOffice(),
                arm.getMol(),
                arm.getMolFio(),
                arm.getDescription(),
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getId()
                        : -1L,
                arm.getPointOfPresence() != null
                        ? arm.getPointOfPresence().getShortName()
                        : null,
                armDetails
        );
    }

}
