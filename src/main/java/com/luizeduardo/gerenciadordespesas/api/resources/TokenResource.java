package com.luizeduardo.gerenciadordespesas.api.resources;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizeduardo.gerenciadordespesas.api.config.property.GerenciadorDeDespesasProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@Autowired
	private GerenciadorDeDespesasProperty gerenciadorDeDespesasProperty;

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse res) {

		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(gerenciadorDeDespesasProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);

		res.addCookie(cookie);
		res.setStatus(HttpStatus.NO_CONTENT.value());

	}
}
