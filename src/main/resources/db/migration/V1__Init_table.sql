drop table if exists department;
drop table if exists departments_lectors;
drop table if exists lector;

create table department
(
    id                    bigint not null auto_increment,
    name                  varchar(255),
    head_of_department_id bigint,
    primary key (id)
);

create table departments_lectors
(
    department_id bigint not null,
    lector_id     bigint not null
);

create table lector
(
    id         bigint not null auto_increment,
    degree     varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    salary     decimal(38, 2),
    primary key (id)
);

alter table department
    add constraint fk_departments_lectors foreign key (head_of_department_id) references lector (id);

alter table departments_lectors
    add constraint fk_departments_lectors_lectors foreign key (lector_id) references lector (id);

alter table departments_lectors
    add constraint fk_departments_lectors_departments foreign key (department_id) references department (id)