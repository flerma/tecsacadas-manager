insert into usuario (id, nome, login, senha, ativo) values (1, 'Fernando', 'flerma', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true);
insert into usuario (id, nome, login, senha, ativo) values (2, 'Pedro Lerma', 'plerma', '$2a$10$8/ijdzk/ccqx8rFNKVj7i.gJRp0CTtDPVKqHoR0AkvFTlUzNCxA02', true);

insert into grupo (id, nome, descricao) values (1, 'DIRETOR', 'Grupo de diretores');
insert into grupo (id, nome, descricao) values (2, 'GERENTE', 'Grupo de gerentes');

insert into permissao (id, nome) values (1, 'ADMIN');
insert into permissao (id, nome) values (2, 'USER');

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (2, 1);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 2);