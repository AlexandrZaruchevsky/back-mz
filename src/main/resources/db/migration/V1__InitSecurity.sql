create table if not exists users
(
    id           bigint       not null auto_increment primary key,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'NOT_ACTIVE',
    username     varchar(32)  not null,
    password     varchar(256) not null,
    email        varchar(256),
    last_name    varchar(64),
    first_name   varchar(64),
    middle_name  varchar(64),
    save_by_user varchar(32),
    unique unq_users_username (username),
    unique unq_users_email (email),
    index idx_users_fio (last_name, first_name, middle_name)
);

create table if not exists roles
(
    id           bigint auto_increment primary key not null,
    created      timestamp   default current_timestamp,
    updated      timestamp on update current_timestamp,
    status       varchar(16) default 'ACTIVE',
    name         varchar(64)                       not null,
    save_by_user varchar(32),
    unique unq_roles_name (name, status)
);

create table if not exists user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint fk_user_role_user_id foreign key (user_id)
        references users (id)
        on delete cascade on update cascade,
    constraint fk_user_role_role_id foreign key (role_id)
        references roles (id)
        on delete cascade on update cascade
);

create table if not exists role_permission
(
    role_id      bigint      not null,
    permission   varchar(64) not null,
    primary key (role_id, permission),
    constraint fk_role_permission_role_id foreign key (role_id)
        references roles (id)
        on delete cascade on update cascade
);
