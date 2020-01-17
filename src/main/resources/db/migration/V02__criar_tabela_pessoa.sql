CREATE TABLE pessoa(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	ativo boolean,
	logadouro VARCHAR(100),
	numero VARCHAR(20),
	complemento VARCHAR(100),
	bairro VARCHAR(100),
	cep VARCHAR(50),
	cidade VARCHAR(100),
	estado VARCHAR(500)
) ENGINE=InnoDB;