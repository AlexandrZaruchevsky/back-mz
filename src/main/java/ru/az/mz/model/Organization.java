package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "organization")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Organization.withDepartments",
                attributeNodes = {
                        @NamedAttributeNode("departments")
                }
        ),
        @NamedEntityGraph(
                name = "Organization.withPositions",
                attributeNodes = {
                        @NamedAttributeNode("positions")
                }
        ),
        @NamedEntityGraph(
                name = "Organization.withPointOfPresence",
                attributeNodes = {
                        @NamedAttributeNode("pointOfPresences")
                }
        )
})
public class Organization extends BaseEntity {

    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "ogrn")
    private String ogrn;
    @Column(name = "boss_id")
    private Long bossId;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<PointOfPresence> pointOfPresences;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Department> departments;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Position> positions;

}
