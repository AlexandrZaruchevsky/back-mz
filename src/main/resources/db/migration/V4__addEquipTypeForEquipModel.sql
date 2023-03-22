alter table equip_model
    add column equip_type_id bigint;

alter table equip_model
    add constraint fk_equip_model_equip_type_id foreign key (equip_type_id)
            references equip_type(id);