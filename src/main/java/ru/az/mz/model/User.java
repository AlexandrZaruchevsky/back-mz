package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@ToString(
        callSuper = true,
        exclude = "roles"
)
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "User.withAll",
                attributeNodes = @NamedAttributeNode(
                        value = "roles",
                        subgraph = "roles.permissions"
                ),
                subgraphs = @NamedSubgraph(
                        name = "roles.permissions",
                        attributeNodes = @NamedAttributeNode("permissions")
                )
        ),
        @NamedEntityGraph(
                name = "User.withRoles",
                includeAllAttributes = true
        )
})
public class User extends BaseEntity{

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private List<Role> roles;

}
