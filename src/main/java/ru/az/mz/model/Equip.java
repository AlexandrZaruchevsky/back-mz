package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "equip")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Equip.withAll",
                includeAllAttributes = true
        ),
        @NamedEntityGraph(
                name = "Equip.withEquipType",
                attributeNodes = {
                        @NamedAttributeNode("equipType"),
                        @NamedAttributeNode("armDetail"),
                }
        ),
        @NamedEntityGraph(
                name = "Equip.withEquipModel",
                attributeNodes = @NamedAttributeNode("equipModel")
        ),
        @NamedEntityGraph(
                name = "equip.withEquipTypeAndEquipModel",
                attributeNodes = {
                        @NamedAttributeNode("equipType"),
                        @NamedAttributeNode("equipModel")
                }
        )
})
public class Equip extends BaseEntity{

    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "inventory_number")
    private String inventoryNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "date_of_manufacture")
    private LocalDate dateOfManufacture;
    @Column(name = "group_accounting")
    private boolean groupAccounting;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "children")
    private boolean children;
    @Column(name = "employee_mol")
    private String employeeMol;
    @Column(name = "mol_fio")
    private String molFio;
    @Column(name = "ip_v4")
    private String ipV4;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "arm_id")
//    private Arm arm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equip_type_id")
    private EquipType equipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equip_model_id")
    private EquipModel equipModel;


    @OneToOne(mappedBy = "equip", fetch = FetchType.LAZY)
    private ArmDetail armDetail;

}
