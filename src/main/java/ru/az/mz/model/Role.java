package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@ToString(callSuper = true, exclude = "users")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Role.withoutUser",
                attributeNodes = {
                        @NamedAttributeNode("permissions")
                }),
        @NamedEntityGraph(
                name = "Role.withAll",
                includeAllAttributes = true
        )
})
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private Set<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
