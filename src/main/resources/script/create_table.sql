drop table if exists person;

drop sequence if exists per_seq;

create table person
(
    per_id     serial primary key not null,
    per_nom    varchar(100)       not null,
    per_prenom varchar(100)       not null,
    per_age    int                not null,
    per_date   date
);

create sequence excel_per_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;