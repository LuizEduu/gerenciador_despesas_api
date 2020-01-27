package com.luizeduardo.gerenciadordespesas.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luizeduardo.gerenciadordespesas.api.config.property.GerenciadorDeDespesasProperty;

/**
 * Classe Responsável por filtrar as requisições e dar as permissões para o CORS
 * 
 * @author luiz
 *
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Autowired
	private GerenciadorDeDespesasProperty gerenciadorDeDespesasProperty;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin",
				gerenciadorDeDespesasProperty.getOriginPermitida());

		// se o metodo da requisição for um Options
		if (request.getMethod().equals("OPTIONS")
				&& request.getHeader("Origin").equals(gerenciadorDeDespesasProperty.getOriginPermitida())) {

			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Max-Age", "3600");

			response.setStatus(HttpServletResponse.SC_OK);

		} else {
			chain.doFilter(req, res);
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
