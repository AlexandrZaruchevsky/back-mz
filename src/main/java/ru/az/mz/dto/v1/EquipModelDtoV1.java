package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.EquipModel;

@Value
public class EquipModelDtoV1 {

    Long id;
    String name;
    EquipTypeDtoV1 equipType;

    private static EquipModelDtoV1 createDefault(EquipModel equipModel, EquipTypeDtoV1 equipType) {
        return new EquipModelDtoV1(
                equipModel.getId(),
                equipModel.getName(),
                equipType
        );
    }

    public static EquipModelDtoV1 create(EquipModel equipModel) {
        return createDefault(
                equipModel,
                null
        );
    }

    public static EquipModelDtoV1 createWithEquipType(EquipModel equipModel) {
        return createDefault(
                equipModel,
                equipModel.getEquipType() != null
                        ? EquipTypeDtoV1.create(equipModel.getEquipType())
                        : null
        );
    }

}
