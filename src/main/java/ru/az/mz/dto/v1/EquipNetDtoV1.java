package ru.az.mz.dto.v1;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ru.az.mz.model.EquipNet;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class EquipNetDtoV1 {

    private Long id;
    private String hostName;
    private String canonicalHostName;
    private String ipV4;
    private int pingTime;
    private LocalDateTime lastActive;
    private byte[] glpiInfoXml;
    private LocalDateTime lastUpdateGlpiInfo;
    private Long subnetMapId;
    private String subnetMapName;

    public static Optional<EquipNetDtoV1> create(EquipNet equipNet) {
        if (equipNet == null) return Optional.empty();
        EquipNetDtoV1 equipNetDtoV1 = new EquipNetDtoV1();
        BeanUtils.copyProperties(equipNet, equipNetDtoV1);
        return Optional.of(equipNetDtoV1);
    }

}
