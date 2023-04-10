package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"organization", "employees"})
@Data
@Entity
@Table(name = "department")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Department.withOrganization",
                attributeNodes = @NamedAttributeNode("organization")
        ),
        @NamedEntityGraph(
                name = "Department.withOrganizationAndEmployees",
                attributeNodes = {
                        @NamedAttributeNode("organization"),
                        @NamedAttributeNode("employees")
                }
        ),
        @NamedEntityGraph(
                name = "Department.withEmployees",
                attributeNodes = @NamedAttributeNode("employees")
        )
})
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "top_level")
    private boolean topLevel;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "boss_id")
    private Long bossId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

}
