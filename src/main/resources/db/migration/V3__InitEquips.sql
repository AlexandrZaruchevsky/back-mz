create table if not exists arm
(
    id                   bigint not null auto_increment primary key,
    created              timestamp   default current_timestamp,
    updated              timestamp on update current_timestamp,
    status               varchar(16) default 'ACTIVE',
    name                 varchar(128),
    office               varchar(32),
    point_of_presence_id bigint,
    employee_id          bigint,
    save_by_user         varchar(32),
    index idx_arm_name (name),
    constraint fk_arm_point_of_presence_id foreign key (point_of_presence_id)
        references point_of_presence (id),
    constraint fk_arm_employee_id foreign key (employee_id)
        references employee (id)
);

create table if not exists equip_type
(
    id           bigint not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'ACTIVE',
    name         varchar(128),
    save_by_user varchar(32),
    index idx_equip_type_name (name)
);

create table if not exists equip_model
(
    id           bigint not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'ACTIVE',
    name         varchar(128),
    save_by_user varchar(32),
    index idx_equip_model_name (name)
);

create table if not exists equip
(
    id                  bigint not null auto_increment primary key,
    created             timestamp   default current_timestamp,
    updated             timestamp on update current_timestamp,
    status              varchar(16) default 'ACTIVE',
    short_name          varchar(128),
    full_name           varchar(256),
    serial_number       varchar(32),
    inventory_number    varchar(32),
    manufacturer        varchar(128),
    date_of_manufacture date,
    arm_id              bigint,
    equip_type_id       bigint,
    equip_model_id      bigint,
    save_by_user        varchar(32),
    index idx_equip_short_name (short_name),
    index idx_equip_inventory_number(inventory_number),
    constraint fk_equip_arm_id foreign key(arm_id)
            references arm(id),
    constraint fk_equip_equip_type_id foreign key (equip_type_id)
            references equip_type(id),
    constraint fk_equip_equip_model_id foreign key (equip_model_id)
            references equip_model(id)
);