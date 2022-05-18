drop table if exists lessons;
drop table if exists persons;
drop table if exists subscriptions;

create table lessons (
    id bigint not null auto_increment,
    comment varchar(255),
    lesson_date time,
    lesson_desc varchar(255),
    duration integer,
    home_work varchar(255),
    teacher_id bigint,
    primary key (id));

create table persons (
    id bigint not null auto_increment,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    surname varchar(255),
    primary key (id));

create table subscriptions (
    lesson_id bigint not null,
    person_id bigint not null);

alter table persons add constraint UK_1x5aosta48fbss4d5b3kuu0rd unique (email);
alter table lessons add constraint FKffnkqqgifjm9agaxusyrmjsl foreign key (teacher_id) references persons (id);
alter table subscriptions add constraint FKpbh3yob6tb0s5e042o369jnhv foreign key (person_id) references persons (id);
alter table subscriptions add constraint FKlqlsoks080lmc0929ggfl99od foreign key (lesson_id) references lessons (id);



