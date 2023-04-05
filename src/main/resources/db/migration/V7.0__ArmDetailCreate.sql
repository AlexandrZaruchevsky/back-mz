create table if not exists arm_detail
(
    id           bigint not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'NOT_ACTIVE',
    arm_id       bigint,
    name         varchar(128),
    domain_name  varchar(256),
    ip_v4        varchar(16),
    description  varchar(1024),
    equip_id     bigint,
    save_by_user varchar(32),
    index idx_arm_detail_name (name),
    index idx_arm_detail_domain_name (domain_name),
    constraint fk_arm_detail_arm_id foreign key (arm_id)
        references arm (id),
    constraint fk_arm_detail_equip_id foreign key (equip_id)
        references equip(id)
);