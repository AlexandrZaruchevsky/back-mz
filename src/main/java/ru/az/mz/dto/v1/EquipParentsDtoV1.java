package ru.az.mz.dto.v1;

import lombok.Value;

import java.util.List;

@Value
public class EquipParentsDtoV1 {

    List<ArmDtoV1> arms;
    List<EquipTypeDtoV1> equipTypes;
    List<EquipModelDtoV1> equipModels;

}
