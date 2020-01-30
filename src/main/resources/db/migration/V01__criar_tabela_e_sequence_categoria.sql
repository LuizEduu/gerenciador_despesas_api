create table if not exists public.categoria (
  id integer,
  nome character varying(50) not null,
  primary key(id)
);

CREATE SEQUENCE if not exists public.categoria_id
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
 
ALTER TABLE public.categoria ALTER COLUMN id SET DEFAULT NEXTVAL('public.categoria_id'::regclass);

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Outros');