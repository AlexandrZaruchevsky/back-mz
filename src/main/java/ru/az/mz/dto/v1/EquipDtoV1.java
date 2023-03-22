package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Equip;

import java.time.LocalDate;

@Value
public class EquipDtoV1 {

    Long id;
    String shortName;
    String fullName;
    String serialNumber;
    String inventoryNumber;
    String manufacturer;
    LocalDate dateOfManufacture;

    ArmDtoV1 arm;
    EquipTypeDtoV1 equipType;
    EquipModelDtoV1 equipModel;

    private static EquipDtoV1 createDefault(
            Equip equip,
            ArmDtoV1 arm,
            EquipTypeDtoV1 equipType,
            EquipModelDtoV1 equipModel
    ) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                arm,
                equipType,
                equipModel
        );
    }

    public static EquipDtoV1 create(Equip equip) {
        return createDefault(
                equip,
                null,
                null,
                null
        );
    }

    public static EquipDtoV1 createWithAll(Equip equip) {
        return createDefault(
                equip,
                equip.getArm() != null
                        ? ArmDtoV1.create(equip.getArm())
                        : null,
                equip.getEquipType() != null
                        ? EquipTypeDtoV1.create(equip.getEquipType())
                        : null,
                equip.getEquipModel() != null
                        ? EquipModelDtoV1.create(equip.getEquipModel())
                        : null
        );
    }

}
