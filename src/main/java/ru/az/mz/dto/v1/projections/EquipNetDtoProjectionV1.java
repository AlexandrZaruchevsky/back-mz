package ru.az.mz.dto.v1.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public interface EquipNetDtoProjectionV1 {

    @JsonProperty("id")
    Long getId();

    @JsonProperty("hostName")
    String getHostName();

    @JsonProperty("canonicalHostName")
    String getCanonicalHostName();

    @JsonProperty("ipV4")
    String getIpV4();

    @JsonProperty("pingTime")
    Integer getPingTime();

    @JsonProperty("lastActive")
    LocalDateTime getLastActive();

    @JsonProperty("lastUpdateGlpiInfo")
    LocalDateTime getLastUpdateGlpiInfo();

    @JsonProperty("subnetId")
    Long getSubnetMapId();

}
