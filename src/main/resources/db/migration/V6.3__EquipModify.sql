alter table equip
    add column employee_mol varchar(64);

create index idx_equip_employee_mol on equip(employee_mol);