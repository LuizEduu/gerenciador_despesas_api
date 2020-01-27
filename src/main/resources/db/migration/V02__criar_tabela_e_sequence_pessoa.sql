create table if not exists public.pessoa (
	id integer,
  nome character varying(50) not null,
  ativo boolean not null,
  logradouro character varying(50),
	numero character varying(10) ,
	complemento character varying(50),
	bairro character varying(100),
	cep character varying(50),
	cidade character varying(100),
	estado character varying(100),
  primary key(id)
);

CREATE SEQUENCE if not exists public.pessoa_id
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
 
ALTER TABLE public.pessoa ALTER COLUMN id SET DEFAULT NEXTVAL('public.pessoa_id'::regclass);