package com.luizeduardo.gerenciadordespesas.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizeduardo.gerenciadordespesas.api.event.RecursoCriadoEvent;

/**
 * Classe Responsável por ouvir o evento quando um recurso é criado na aplicação
 * 
 * @author luiz
 */

@Component // anotação responsável por tornar esta classe um componente do Spring
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse httpServletResponse = event.getHttpServletResponse();
		Long id = event.getId();

		adicionarHeaderLocation(httpServletResponse, id);
	}

	private void adicionarHeaderLocation(HttpServletResponse httpServletResponse, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		httpServletResponse.setHeader("Location", uri.toASCIIString());
	}

	
}
