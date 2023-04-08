package ru.az.mz.util.equip.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquipCsv {

    private String name;
    private String inventoryNumber;
    private String smetaCode;
    private String quantity;
    private String apartment;
    private String accountName;

}
