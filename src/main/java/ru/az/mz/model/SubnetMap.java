package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "subnet_map")
public class SubnetMap extends BaseEntity {

    @Column(name = "subnet_name")
    private String subnetName;

    @Lob
    @Column(name = "subnet_map_xml", columnDefinition = "BLOB")
    private byte[] subnetMapXml;

}
