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
    Long armId;
    String armName;
    Long equipTypeId;
    String equipTypeName;
    Long equipModelId;
    String equipModelName;


    public static EquipDtoV1 create(Equip equip) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static EquipDtoV1 createWithAll(Equip equip) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

}
