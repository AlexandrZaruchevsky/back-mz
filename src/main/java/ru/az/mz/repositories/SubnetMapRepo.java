package ru.az.mz.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.dto.v1.projections.SubnetMapProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.SubnetMap;

import java.util.List;
import java.util.Optional;

public interface SubnetMapRepo extends PagingAndSortingRepository<SubnetMap, Long> {

    Optional<SubnetMap> findByStatusAndId(EntityStatus status, Long id);

    List<SubnetMap> findAllByStatusAndSubnetNameContaining(EntityStatus status, String subnetName);

    List<SubnetMap> findAllBySubnetNameContaining(String subnetName);

    List<SubnetMap> findAll();

    @Query("select sm.id, sm.subnetName, sm.status from SubnetMap sm order by sm.subnetName")
    List<SubnetMapProjectionV1> findAllAsProjection();

    @Query("select distinct sm.subnetName from SubnetMap sm order by sm.subnetName")
    List<String> findSubnetNames();

}
