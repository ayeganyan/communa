insert into resident(name, surname, email, password, birthdate)
    values ('Thomas', 'Mann', 'tmann@mail.com', 'password', '2000-01-01 00:00:01');
insert into resident(name, surname, email, password, birthdate)
    values ('Herman', 'Hesse', 'hhesse@mail.com', 'password', '2000-01-01 00:00:01');

insert into community(id, name, description) values (1, 'yeraz', 'Adonts st');
insert into community(id, name, description) values (2, 'Veratsnund', 'Monte Melkonyan st');

insert into parkinglot(code, community_id)  values ('V11', 1);
insert into parkinglot(code, community_id)  values ('V12', 1);
insert into parkinglot(code, community_id)  values ('V13', 1);
insert into parkinglot(code, community_id)  values ('V22', 1);

insert into parkinglot(code, community_id)  values ('A11', 2);
insert into parkinglot(code, community_id)  values ('A12', 2);
insert into parkinglot(code, community_id)  values ('B13', 2);
insert into parkinglot(code, community_id)  values ('B22', 2);