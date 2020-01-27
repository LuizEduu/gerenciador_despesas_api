create table if not exists public.lancamento(
   id integer,
   descricao character varying (200) not null,
   data_vencimento date not null,
   data_pagamento date not null,
   valor decimal not null,
   observacao character varying (100),
   tipo character varying (50),
   id_categoria integer,
   id_pessoa integer,
   FOREIGN KEY (id_categoria) references categoria (id),
   FOREIGN KEY (id_pessoa) references pessoa (id),
   PRIMARY KEY (id)
);

CREATE SEQUENCE if not exists public.lancamento_id
 	INCREMENT 1
 	MINVALUE 1 
 	MAXVALUE 9223372036854775807 
 	START 1 
 	CACHE 1;

ALTER TABLE public.lancamento ALTER COLUMN id SET DEFAULT NEXTVAL ('public.lancamento_id'::regclass);