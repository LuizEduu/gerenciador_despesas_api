create table if not exists public.lancamento(
   id integer,
   descricao character varying (200) not null,
   data_vencimento date not null,
   data_pagamento date,
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


INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Supermercado', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Academia', '2017-06-10', null, 120, null, 'DESPESA', 3, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Conta de luz', '2017-02-10', '2017-02-10', 110.44, null, 'DESPESA', 3, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Conta de água', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Restaurante', '2017-03-10', '2017-03-10', 1010.32, null, 'DESPESA', 4, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Venda vídeo game', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Clube', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Impostos', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Multa', '2017-04-10', '2017-04-10', 665.33, null, 'DESPESA', 5, 10);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Padaria', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Papelaria', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Almoço', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);