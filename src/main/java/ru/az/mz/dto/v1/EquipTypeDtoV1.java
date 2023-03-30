package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.EquipType;

import java.util.Collections;
import java.util.List;

@Value
public class EquipTypeDtoV1 {

    Long id;
    String name;
    List<EquipModelDtoV1> equipModels;
    List<EquipDtoV1> equips;

    public static EquipTypeDtoV1 create(EquipType equipType){
        return new EquipTypeDtoV1(
                equipType.getId(),
                equipType.getName(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

}
