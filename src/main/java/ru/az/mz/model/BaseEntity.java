package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EntityStatus status;

    @Column(name = "save_by_user")
    private String saveByUser;

    @PrePersist
    private void entityCreate() {
        this.created = LocalDateTime.now();
        this.status = EntityStatus.ACTIVE;
    }

    @PreUpdate
    private void entityUpdated() {
        this.updated = LocalDateTime.now();
    }

}
