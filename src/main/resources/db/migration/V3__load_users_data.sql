insert into `group` (id, name, description) values (1, 'DIRETOR', 'group de diretores');
insert into `group` (id, name, description) values (2, 'GERENTE', 'group de gerentes');
insert into `group` (id, name, description) values (3, 'SUPERVISOR', 'group de supervisores');

insert into user (id, name, login, password, active, main_group_id) values (1, 'Fernando', 'flerma', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true, 1);
insert into user (id, name, login, password, active, main_group_id) values (2, 'Pedro Lerma', 'plerma', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true, 1);
insert into user (id, name, login, password, active, main_group_id) values (3, 'Gerente', 'gerente', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true, 2);
insert into user (id, name, login, password, active, main_group_id) values (4, 'Supervisor', 'supervisor', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true, 3);

insert into permission (id, name) values (1, 'ADMIN');
insert into permission (id, name) values (2, 'USER');

insert into user_group (user_id, group_id) values (1, 1);
insert into user_group (user_id, group_id) values (1, 2);
insert into user_group (user_id, group_id) values (2, 1);
insert into user_group (user_id, group_id) values (3, 2);
insert into user_group (user_id, group_id) values (4, 3);

insert into group_permission (group_id, permission_id) values (1, 1);
insert into group_permission (group_id, permission_id) values (1, 2);
