package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "equip_net")
public class EquipNet extends BaseEntity {

    @Column(name = "host_name")
    private String hostName;
    @Column(name = "canonical_host_name")
    private String canonicalHostName;
    @Column(name = "ip_v4")
    private String ipV4;
    @Column(name = "ping_time")
    private int pingTime;
    @Column(name = "last_active")
    private LocalDateTime lastActive;

    @Lob
    @Column(name = "glpi_info_xml", columnDefinition = "MEDIUMBLOB")
    private byte[] glpiInfoXml;

    @Column(name = "last_update_glpi_info")
    private LocalDateTime lastUpdateGlpiInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subnet_map_id")
    private SubnetMap subnetMap;



}
