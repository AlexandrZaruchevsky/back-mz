package ru.az.mz.dto.v1.projections;

import java.time.LocalDateTime;

public interface EquipNetDtoProjectionV1 {

    Long getId();

    String getHostName();

    String getCanonicalHostName();

    String getIpV4();

    Integer getPingTime();

    LocalDateTime getLastActive();

    LocalDateTime getLastUpdateGlpiInfo();

    Long getSubnetMapId();

}
