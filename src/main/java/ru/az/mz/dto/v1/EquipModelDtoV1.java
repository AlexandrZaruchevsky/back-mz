package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.EquipModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class EquipModelDtoV1 {

    Long id;
    String name;
    Long equipTypeId;
    String equipTypeName;
    List<EquipDtoV1> equips;

    public static EquipModelDtoV1 create(EquipModel equipModel) {
        return new EquipModelDtoV1(
                equipModel.getId(),
                equipModel.getName(),
                null,
                null,
                Collections.emptyList()
        );
    }

    public static EquipModelDtoV1 createWithEquipType(EquipModel equipModel) {
        return new EquipModelDtoV1(
                equipModel.getId(),
                equipModel.getName(),
                equipModel.getEquipType() != null
                        ? equipModel.getEquipType().getId()
                        : null,
                equipModel.getEquipType() != null
                        ? equipModel.getEquipType().getName()
                        : null,
                Collections.emptyList()
        );
    }

    public static EquipModelDtoV1 createWithAll(EquipModel equipModel) {
        return new EquipModelDtoV1(
                equipModel.getId(),
                equipModel.getName(),
                equipModel.getEquipType() != null
                        ? equipModel.getEquipType().getId()
                        : null,
                equipModel.getEquipType() != null
                        ? equipModel.getEquipType().getName()
                        : null,
                equipModel.getEquips() != null
                        ? equipModel.getEquips().stream().map(EquipDtoV1::create).collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }


}
