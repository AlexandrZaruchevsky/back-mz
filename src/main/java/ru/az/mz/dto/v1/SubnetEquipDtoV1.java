package ru.az.mz.dto.v1;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ru.az.mz.services.utils.xml.SubnetEquipXml;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class SubnetEquipDtoV1 {

    private Long id;
    private String hostAddress;
    private String hostName;
    private String canonicalHostName;
    private boolean active;
    private long pingTime;
    private LocalDateTime pingCurrentTime;

    public static Optional<SubnetEquipDtoV1> create(SubnetEquipXml subnetEquipXml) {
        if (subnetEquipXml == null) return Optional.empty();
        SubnetEquipDtoV1 subnetEquipDtoV1 = new SubnetEquipDtoV1();
        BeanUtils.copyProperties(subnetEquipXml, subnetEquipDtoV1);
        return Optional.of(subnetEquipDtoV1);
    }

}
