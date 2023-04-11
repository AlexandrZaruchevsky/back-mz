package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import ru.az.mz.model.Equip;

import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipInfoV1 {

    private Long id;
    private String shortName;
    private String fullName;
    private String serialNumber;
    private String inventoryNumber;
    private String manufacturer;
    private LocalDate dateOfManufacture;
    private boolean groupAccounting;
    private Long parentId;
    private boolean children;
    private String employeeMol;
    private String molFio;
    private String ipV4;
    private Long equipTypeId;
    private String equipTypeName;
    private Long equipModelId;
    private String equipModelName;

    public static Optional<EquipInfoV1> create(Equip equip) {
        if (equip == null) return Optional.empty();
        EquipInfoV1 equipInfoV1 = new EquipInfoV1();
        BeanUtils.copyProperties(equip, equipInfoV1);
        if (equip.getEquipType() != null) {
            equipInfoV1.setEquipTypeId(equip.getEquipType().getId());
            equipInfoV1.setEquipTypeName(equip.getEquipType().getName());
        }
        if (equip.getEquipModel()!=null){
            equipInfoV1.setEquipModelId(equip.getEquipModel().getId());
            equipInfoV1.setEquipModelName(equip.getEquipModel().getName());
        }
        return Optional.of(equipInfoV1);
    }

}
