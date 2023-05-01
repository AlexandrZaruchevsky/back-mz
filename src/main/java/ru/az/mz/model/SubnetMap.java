package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "subnet_map")
public class SubnetMap extends BaseEntity {

    @Column(name = "subnet_name")
    private String subnetName;

    @Lob
    @Column(name = "subnet_map_xml", columnDefinition = "MEDIUMBLOB")
    private byte[] subnetMapXml;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subnetMap")
    private List<EquipNet> equipNets;

}
