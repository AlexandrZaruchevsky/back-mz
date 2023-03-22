package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.EquipType;

@Value
public class EquipTypeDtoV1 {

    Long id;
    String name;

    public static EquipTypeDtoV1 create(EquipType equipType){
        return new EquipTypeDtoV1(equipType.getId(), equipType.getName());
    }

}
