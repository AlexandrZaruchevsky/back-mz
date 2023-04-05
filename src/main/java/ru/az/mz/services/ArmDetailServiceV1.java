package ru.az.mz.services;

import ru.az.mz.dto.v1.ArmDetailDtoV1;
import ru.az.mz.model.ArmDetail;

import java.util.List;

public interface ArmDetailServiceV1 extends CrudServiceV1<ArmDetail, ArmDetailDtoV1> {

    List<ArmDetailDtoV1> findAllByArm(Long id);

}
