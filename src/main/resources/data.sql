insert into community(id, name, description) values (1, 'Yeraz', 'Adonts st');
insert into community(id, name, description) values (2, 'Veratsnund', 'Monte Melkonyan st');

insert into parkinglot(code, fk_community)  values ('V11', 1);
insert into parkinglot(code, fk_community)  values ('V12', 1);
insert into parkinglot(code, fk_community)  values ('V13', 1);
insert into parkinglot(code, fk_community)  values ('V22', 1);

insert into parkinglot(code, fk_community)  values ('A11', 2);
insert into parkinglot(code, fk_community)  values ('A12', 2);
insert into parkinglot(code, fk_community)  values ('B13', 2);
insert into parkinglot(code, fk_community)  values ('B22', 2);

insert into resident(name, surname, email, password, birthdate, fk_community)
values ('Thomas', 'Mann', 'tmann@mail.com', 'password', '2000-01-01 00:00:01', 1);
insert into resident(name, surname, email, password, birthdate)
values ('Herman', 'Hesse', 'hhesse@mail.com', 'password', '2000-01-01 00:00:01');