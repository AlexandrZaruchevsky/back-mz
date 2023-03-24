package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "employee")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Employee.withDepartment",
                attributeNodes = {
                        @NamedAttributeNode("department")
                }
        ),
        @NamedEntityGraph(
                name = "Employee.withDepartmentAndPosition",
                attributeNodes = {
                        @NamedAttributeNode("department"),
                        @NamedAttributeNode("position")
                }
        ),
        @NamedEntityGraph(
                name = "Employee.withPosition",
                attributeNodes = {
                        @NamedAttributeNode("position")
                }
        ),
        @NamedEntityGraph(
                name = "Employee.withPointOfPresence",
                attributeNodes = {
                        @NamedAttributeNode("pointOfPresence")
                }
        ),
        @NamedEntityGraph(
                name = "Employee.withAll",
                includeAllAttributes = true
        )
})
public class Employee extends BaseEntity {

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "kspd")
    private String kspd;

    @Column(name = "account_name")
    private String accountName;
    @Column(name = "principal_name")
    private String principalName;
    @Column(name = "description")
    private String description;
    @Column(name = "info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_of_presence_id")
    private PointOfPresence pointOfPresence;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Arm> arms;

}
