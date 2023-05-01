package ru.az.mz.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.projections.SubnetMapProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.SubnetMap;
import ru.az.mz.repositories.SubnetMapRepo;
import ru.az.mz.services.SubnetMapServiceV1;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SubnetMapServiceV1Impl implements SubnetMapServiceV1 {

    private final SubnetMapRepo subnetMapRepo;

    public SubnetMapServiceV1Impl(SubnetMapRepo subnetMapRepo) {
        this.subnetMapRepo = subnetMapRepo;
    }

    @Override
    public List<SubnetMapProjectionV1> findAllByStatus() {
        return subnetMapRepo.findAllAsProjection();
    }

    @Override
    public Optional<SubnetMap> findById(Long id) {
        return Optional.empty();
    }
}
