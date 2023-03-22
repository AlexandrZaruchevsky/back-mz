package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "position")
@ToString(exclude = "employees")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Position.withOrganization",
                attributeNodes = @NamedAttributeNode("organization")
        ),
        @NamedEntityGraph(
                name = "Position.withEmployees",
                attributeNodes = @NamedAttributeNode("employees")
        ),
        @NamedEntityGraph(
                name = "Position.withAll",
                includeAllAttributes = true
        )
})
public class Position extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<Employee> employees;

}
