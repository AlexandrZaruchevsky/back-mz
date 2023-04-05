package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.ArmDetail;

@Value
public class ArmDetailDtoV1 {

    Long id;
    String name;
    String domainName;
    String ipV4;
    String description;
    Long armId;
    String armName;
    Long equipId;
    String equipName;

    public static ArmDetailDtoV1 create(ArmDetail armDetail) {
        return new ArmDetailDtoV1(
                armDetail.getId(),
                armDetail.getName(),
                armDetail.getDomainName(),
                armDetail.getIpV4(),
                armDetail.getDescription(),
                -1L,
                null,
                -1L,
                null
        );
    }

    public static ArmDetailDtoV1 createWithArm(ArmDetail armDetail) {
        return new ArmDetailDtoV1(
                armDetail.getId(),
                armDetail.getName(),
                armDetail.getDomainName(),
                armDetail.getIpV4(),
                armDetail.getDescription(),
                armDetail.getArm() != null
                        ? armDetail.getArm().getId()
                        : -1L,
                armDetail.getArm() != null
                        ? armDetail.getArm().getName()
                        : null,
                -1L,
                null
        );
    }

    public static ArmDetailDtoV1 createWithEquip(ArmDetail armDetail) {
        return new ArmDetailDtoV1(
                armDetail.getId(),
                armDetail.getName(),
                armDetail.getDomainName(),
                armDetail.getIpV4(),
                armDetail.getDescription(),
                -1L,
                null,
                armDetail.getEquip() != null
                        ? armDetail.getEquip().getId()
                        : -1L,
                armDetail.getEquip() != null
                        ? armDetail.getEquip().getShortName()
                        : null
        );
    }

    public static ArmDetailDtoV1 createWithAll(ArmDetail armDetail) {
        return new ArmDetailDtoV1(
                armDetail.getId(),
                armDetail.getName(),
                armDetail.getDomainName(),
                armDetail.getIpV4(),
                armDetail.getDescription(),
                armDetail.getArm() != null
                        ? armDetail.getArm().getId()
                        : -1L,
                armDetail.getArm() != null
                        ? armDetail.getArm().getName()
                        : null,
                armDetail.getEquip() != null
                        ? armDetail.getEquip().getId()
                        : -1L,
                armDetail.getEquip() != null
                        ? armDetail.getEquip().getShortName()
                        : null
        );
    }

}
