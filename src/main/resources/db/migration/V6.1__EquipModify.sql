alter table equip
    add group_accounting boolean not null default false;

alter table equip
    add parent_id bigint;

create index idx_equip_parent_id on equip(parent_id);