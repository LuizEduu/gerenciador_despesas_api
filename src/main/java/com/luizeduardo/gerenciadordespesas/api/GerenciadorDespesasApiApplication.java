package com.luizeduardo.gerenciadordespesas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.luizeduardo.gerenciadordespesas.api.config.property.GerenciadorDeDespesasProperty;

@SpringBootApplication
@EnableConfigurationProperties(GerenciadorDeDespesasProperty.class)
public class GerenciadorDespesasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDespesasApiApplication.class, args);
	}

}
