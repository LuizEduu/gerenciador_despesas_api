create table if not exists public.usuario (
  id integer,
  nome character varying(200) not null,
  email character varying(200) not null unique,
  senha character varying(200) not null,
  primary key (id)
);

CREATE SEQUENCE if not exists public.usuario_id
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
 
ALTER TABLE public.usuario ALTER COLUMN id SET DEFAULT NEXTVAL('public.usuario_id'::regclass);

create table if not exists public.permissao(
  id integer unique,
  descricao character varying(200) not null,
  primary key(id)
);


create table if not exists public.usuario_permissao(
  id_usuario integer,
  id_permissao integer,
  foreign key (id_usuario) references usuario(id),
  foreign key (id_permissao) references permissao(id)
);

INSERT INTO public.usuario (id, nome, email, senha) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO public.usuario (id, nome, email, senha) values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO public.permissao (id, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO public.permissao (id, descricao) values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO public.permissao (id, descricao) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO public.permissao (id, descricao) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO public.permissao (id, descricao) values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO public.permissao (id, descricao) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO public.permissao (id, descricao) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO public.permissao (id, descricao) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (1, 8);

-- maria
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO public.usuario_permissao (id_usuario, id_permissao) values (2, 8);