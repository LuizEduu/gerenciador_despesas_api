CREATE TABLE categoria(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)Engine=InnoDB;

INSERT INTO categoria(nome) VALUES ('Restaurante');
INSERT INTO categoria(nome) VALUES ('Casa');
INSERT INTO categoria(nome) VALUES ('Carro');   