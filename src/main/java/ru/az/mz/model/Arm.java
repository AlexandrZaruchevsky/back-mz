package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = {
        "armDetails"
})
@Entity
@Table(name = "arm")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Arm.withPointOfPresence",
                attributeNodes = @NamedAttributeNode("pointOfPresence")
        ),
        @NamedEntityGraph(
                name = "Arm.withAll",
                includeAllAttributes = true
        )
})
public class Arm extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "office")
    private String office;
    @Column(name = "mol")
    private String mol;
    @Column(name = "mol_fio")
    private String molFio;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_of_presence_id")
    private PointOfPresence pointOfPresence;

    @OneToMany(mappedBy = "arm", fetch = FetchType.LAZY)
    private List<ArmDetail> armDetails;

}
