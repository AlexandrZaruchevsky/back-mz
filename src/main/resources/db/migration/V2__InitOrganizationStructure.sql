create table if not exists organization
(
    id           bigint not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'ACTIVE',
    short_name   varchar(128),
    full_name    varchar(512),
    inn          varchar(16),
    kpp          varchar(16),
    ogrn         varchar(32),
    boss_id      bigint,
    save_by_user varchar(32),
    index idx_organizations_short_name (short_name),
    index idx_organizations_inn (inn),
    index idx_organizations_ogrn (ogrn)
);

create table if not exists department
(
    id              bigint not null auto_increment primary key,
    created         timestamp   default current_timestamp,
    updated         timestamp on update current_timestamp,
    status          varchar(16) default 'ACTIVE',
    name            varchar(256),
    top_level       boolean,
    parent_id       bigint,
    organization_id bigint,
    boss_id         bigint,
    save_by_user    varchar(32),
    index idx_department_name (name),
    constraint fk_department_organization_id foreign key (organization_id)
        references organization (id)
);

create table if not exists point_of_presence
(
    id              bigint not null auto_increment primary key,
    created         timestamp   default current_timestamp,
    updated         timestamp on update current_timestamp,
    status          varchar(16) default 'ACTIVE',
    short_name      varchar(128),
    full_name       varchar(512),
    postcode        varchar(8),
    region          varchar(128),
    district        varchar(128),
    city            varchar(128),
    village         varchar(128),
    street          varchar(128),
    house           varchar(32),
    corpus          varchar(32),
    apartment       varchar(32),
    organization_id bigint,
    boss_id         bigint,
    save_by_user    varchar(32),
    index idx_point_of_presence_short_name (short_name),
    constraint fk_point_of_presence_organization_id foreign key (organization_id)
        references organization (id)
);

create table if not exists position
(
    id              bigint not null auto_increment primary key,
    created         timestamp   default current_timestamp,
    updated         timestamp on update current_timestamp,
    status          varchar(16) default 'ACTIVE',
    name            varchar(256),
    organization_id bigint,
    save_by_user    varchar(32),
    index idx_position_name (name),
    constraint fk_position_organization_id foreign key (organization_id)
        references organization (id)
);

create table if not exists employee
(
    id                   bigint not null auto_increment primary key,
    created              timestamp   default current_timestamp,
    updated              timestamp on update current_timestamp,
    status               varchar(16) default 'ACTIVE',
    last_name            varchar(64),
    first_name           varchar(64),
    middle_name          varchar(64),
    email                varchar(256),
    phone                varchar(16),
    kspd                 varchar(16),
    department_id        bigint,
    position_id          bigint,
    point_of_presence_id bigint,
    user_id              bigint,
    save_by_user         varchar(32),
    index idx_employee_fio (last_name, first_name, middle_name),
    constraint fk_employee_department_id foreign key (department_id)
        references department (id),
    constraint fk_employee_position_id foreign key (position_id)
        references position (id),
    constraint fk_employee_point_of_presence_id foreign key (point_of_presence_id)
        references point_of_presence (id)
);