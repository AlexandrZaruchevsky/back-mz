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
@Table(name = "equip_type")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "EquipType.withEquips",
                attributeNodes = @NamedAttributeNode("equips")
        ),
        @NamedEntityGraph(
                name = "EquipType.withEquipModels",
                attributeNodes = @NamedAttributeNode("equipModels")
        )
})
public class EquipType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipType")
    private List<Equip> equips;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipType")
    private List<EquipModel> equipModels;

}
