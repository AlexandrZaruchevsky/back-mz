create table if not exists equip_net
(
    id                    bigint not null auto_increment primary key,
    created               timestamp   default current_timestamp,
    updated               timestamp on update current_timestamp,
    status                varchar(16) default 'ACTIVE',
    save_by_user          varchar(32),
    host_name             varchar(64) default 'NONAME',
    canonical_host_name   varchar(255),
    ip_v4                 varchar(16),
    ping_time             int,
    last_active           timestamp,
    glpi_info_xml         mediumblob,
    last_update_glpi_info timestamp,
    subnet_map_id         bigint,
    unique unq_equip_net__host_name (status, host_name),
    constraint fk_equip_net__subnet_map_id foreign key (subnet_map_id)
        references subnet_map (id)
);