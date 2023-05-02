package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.az.mz.dto.v1.projections.EquipNetDtoProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipNet;

import java.util.List;
import java.util.Optional;

public interface EquipNetRepo extends PagingAndSortingRepository<EquipNet, Long> {

    @Query(
            value = "select en.id as id, " +
                    "en.host_name as hostName, " +
                    "en.canonical_host_name as canonicalHostName, " +
                    "en.ip_v4 as ipV4, " +
                    "en.ping_time as pingTime, " +
                    "en.last_active as lastActive, " +
                    "en.last_update_glpi_info as lastUpdateGlpiInfo " +
                    "from equip_net en " +
                    "where en.subnet_map_id=:subnetMapId"
            , nativeQuery = true
    )
    List<EquipNetDtoProjectionV1> findAllBySubnetIdAsProjection(@Param("subnetMapId") Long subnetMapId);

    @Query(
            value = "select en.id as id, " +
                    "en.host_name as hostName, " +
                    "en.canonical_host_name as canonicalHostName, " +
                    "en.ip_v4 as ipV4, " +
                    "en.ping_time as pingTime, " +
                    "en.last_active as lastActive, " +
                    "en.last_update_glpi_info as lastUpdateGlpiInfo " +
                    "from equip_net en " +
                    "where en.id=:id"
            , nativeQuery = true
    )
    Optional<EquipNetDtoProjectionV1> findByIdAsProjection(@Param("id") Long id);

    Optional<EquipNet> findByHostName(String hostName);


    @Query(
            value = "select en.id as id, " +
                    "en.host_name as hostName, " +
                    "en.canonical_host_name as canonicalHostName, " +
                    "en.ip_v4 as ipV4, " +
                    "en.ping_time as pingTime, " +
                    "en.last_active as lastActive, " +
                    "en.last_update_glpi_info as lastUpdateGlpiInfo " +
                    "from equip_net en " +
                    "where en.host_name in (:hostNames)",
            nativeQuery = true
    )
    List<EquipNetDtoProjectionV1> findAllByHostNameAsProjection(Iterable<String> hostNames);

//    Page<EquipNetDtoProjectionV1> findAllByStatusProjection(EntityStatus status, )


//    List<EquipNet> f

}
