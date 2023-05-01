package ru.az.mz.services;

import ru.az.mz.dto.v1.projections.SubnetMapProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.SubnetMap;

import java.util.List;
import java.util.Optional;

public interface SubnetMapServiceV1 {

    List<SubnetMapProjectionV1> findAllByStatus();

    Optional<SubnetMap> findById(Long id);

}
