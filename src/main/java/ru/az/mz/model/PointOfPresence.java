package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "point_of_presence")
@ToString(exclude = "employees")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "PointOfPresence.withOrganization",
                attributeNodes = @NamedAttributeNode("organization")
        ),
        @NamedEntityGraph(
                name = "PointOfPresence.withEmployees",
                attributeNodes = @NamedAttributeNode("employees")
        ),
        @NamedEntityGraph(
                name = "PointOfPresence.withOrganizationAndEmployees",
                attributeNodes = {
                        @NamedAttributeNode("organization"),
                        @NamedAttributeNode("employees")
                }
        ),
        @NamedEntityGraph(
                name = "PointOfPresence.withOrganizationAndAmrs",
                attributeNodes = {
                        @NamedAttributeNode("organization"),
                        @NamedAttributeNode("arms")
                }
        )
})
public class PointOfPresence extends BaseEntity {

    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "region")
    private String region;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;
    @Column(name = "village")
    private String village;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "corpus")
    private String corpus;
    @Column(name = "apartment")
    private String apartment;
    @Column(name = "boss_id")
    private Long bossId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "pointOfPresence", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pointOfPresence")
    private List<Arm> arms;

}
