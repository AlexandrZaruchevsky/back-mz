package ru.az.mz.dto.v1;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipNet;
import ru.az.mz.services.utils.xml.SubnetEquipXml;
import ru.az.mz.util.UtilZ;

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

    public static Optional<EquipNet> create(SubnetEquipDtoV1 subnetEquipDtoV1) {
        if (subnetEquipDtoV1 == null) return Optional.empty();
        EquipNet equipNet = new EquipNet();
        BeanUtils.copyProperties(subnetEquipDtoV1, equipNet, "id", "hostName");
        equipNet.setHostName(UtilZ.getHostNameShort(subnetEquipDtoV1.getHostName()));
        equipNet.setIpV4(subnetEquipDtoV1.getHostAddress());
        equipNet.setLastActive(subnetEquipDtoV1.getPingCurrentTime());
        equipNet.setStatus(subnetEquipDtoV1.isActive() ? EntityStatus.ACTIVE : EntityStatus.NOT_ACTIVE);
        return Optional.of(equipNet);
    }

}
