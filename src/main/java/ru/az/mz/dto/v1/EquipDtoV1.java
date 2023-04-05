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
    boolean groupAccounting;
    Long parentId;
    boolean children;
    String employeeMol;
    String molFio;
    String ipV4;
//    Long armId;
//    String armName;
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
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                equip.getMolFio(),
                equip.getIpV4(),
//                -1L,
//                null,
                -1L,
                null,
                -1L,
                null
        );
    }

    public static EquipDtoV1 createWithEquipType(Equip equip) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                equip.getMolFio(),
                equip.getIpV4(),
//                -1L,
//                null,
                equip.getEquipType() != null ? equip.getEquipType().getId() : -1L,
                equip.getEquipType() != null ? equip.getEquipType().getName() : null,
                -1L,
                null
        );
    }


    public static EquipDtoV1 createWithEquipModel(Equip equip) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                equip.getMolFio(),
                equip.getIpV4(),
//                -1L,
//                null,
                -1L,
                null,
                equip.getEquipModel() != null ? equip.getEquipModel().getId() : -1L,
                equip.getEquipModel() != null ? equip.getEquipModel().getName() : null
        );
    }

    public static EquipDtoV1 createWithEquipTypeAndEquipModel(Equip equip) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                equip.getMolFio(),
                equip.getIpV4(),
//                -1L,
//                null,
                equip.getEquipType() != null ? equip.getEquipType().getId() : -1L,
                equip.getEquipType() != null ? equip.getEquipType().getName() : null,
                equip.getEquipModel() != null ? equip.getEquipModel().getId() : -1L,
                equip.getEquipModel() != null ? equip.getEquipModel().getName() : null
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
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                equip.getMolFio(),
                equip.getIpV4(),
//                equip.getArm() != null ? equip.getArm().getId() : -1L,
//                equip.getArm() != null ? equip.getArm().getName() : null,
                equip.getEquipType() != null ? equip.getEquipType().getId() : -1L,
                equip.getEquipType() != null ? equip.getEquipType().getName() : null,
                equip.getEquipModel() != null ? equip.getEquipModel().getId() : -1L,
                equip.getEquipModel() != null ? equip.getEquipModel().getName() : null
        );
    }

    public static EquipDtoV1 createWithAll(Equip equip, String molFio) {
        return new EquipDtoV1(
                equip.getId(),
                equip.getShortName(),
                equip.getFullName(),
                equip.getSerialNumber(),
                equip.getInventoryNumber(),
                equip.getManufacturer(),
                equip.getDateOfManufacture(),
                equip.isGroupAccounting(),
                equip.getParentId(),
                equip.isChildren(),
                equip.getEmployeeMol(),
                molFio,
                equip.getIpV4(),
//                equip.getArm() != null ? equip.getArm().getId() : -1L,
//                equip.getArm() != null ? equip.getArm().getName() : null,
                equip.getEquipType() != null ? equip.getEquipType().getId() : -1L,
                equip.getEquipType() != null ? equip.getEquipType().getName() : null,
                equip.getEquipModel() != null ? equip.getEquipModel().getId() : -1L,
                equip.getEquipModel() != null ? equip.getEquipModel().getName() : null
        );
    }



}
