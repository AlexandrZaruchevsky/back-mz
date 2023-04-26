package ru.az.mz.services.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SubnetEquip {

    private String hostAddress;
    private String hostName;
    private String canonicalHostName;
    private boolean active;
    private long pingTime;
    private LocalDateTime pingCurrentTime;

    public SubnetEquip() {
        hostAddress = "Undefined";
        hostName = "Undefined";
        canonicalHostName = "Undefined";
        active = false;
        pingTime = 0L;
        pingCurrentTime = LocalDateTime.now();
    }

    public SubnetEquip(String hostAddress) {
        this();
        setHostAddress(hostAddress);
    }

}
