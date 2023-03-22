package ru.az.mz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@ToString(exclude = "equips")
@Table(name = "equip_model")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "EquipModel.withAll",
                includeAllAttributes = true
        )
})
public class EquipModel extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipModel")
    private List<Equip> equips;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equip_type_id")
    private EquipType equipType;

}
