package com.luizeduardo.gerenciadordespesas.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * Classe Responsável pela criação do evento de recurso criado
 * aqui eu defino todas as propriedades do evento
 * @author luiz
 *
 */

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse httpServletResponse;
	private Long id;

	public RecursoCriadoEvent(Object source, HttpServletResponse httpServletResponse, Long id) {
		super(source);
		this.httpServletResponse = httpServletResponse;
		this.id = id;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public Long getId() {
		return id;
	}
}