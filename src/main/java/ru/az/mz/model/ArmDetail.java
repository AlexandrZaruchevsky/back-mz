package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "arm_detail")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "ArmDetail.withArm",
                attributeNodes = @NamedAttributeNode("arm")
        ),
        @NamedEntityGraph(
                name = "ArmDetail.withEquip",
                attributeNodes = @NamedAttributeNode("equip")
        ),
        @NamedEntityGraph(
                name = "ArmDetail.withAll",
                includeAllAttributes = true
        )
})
public class ArmDetail extends BaseEntity{

    @Column(name = "name")
    private String name;
    @Column(name = "domain_name")
    private String domainName;
    @Column(name = "ip_v4")
    private String ipV4;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arm_id")
    private Arm arm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equip_id")
    private Equip equip;


}
