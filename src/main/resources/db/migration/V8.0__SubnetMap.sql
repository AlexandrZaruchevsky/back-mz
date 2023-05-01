create table if not exists subnet_map
(
    id           bigint not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'ACTIVE',
    subnet_name  varchar (16) default 'NONAME',
    subnet_map_xml mediumblob,
    save_by_user varchar(32),
    index idx_subnet_map__subnet_name (subnet_name)
);