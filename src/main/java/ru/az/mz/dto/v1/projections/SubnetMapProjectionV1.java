package ru.az.mz.dto.v1.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface SubnetMapProjectionV1 {

    @JsonProperty("id")
    Long getId();
    @JsonProperty("subnetName")
    String getSubnetName();
    @JsonProperty("status")
    String getStatus();

}
