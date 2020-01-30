package com.luizeduardo.gerenciadordespesas.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.luizeduardo.gerenciadordespesas.api.config.token.CustomTokenEnhancer;

/**
 * Classe Responsável pela configuração do servidor de autorização do OAuth2
 * 
 * @author luiz
 *
 */
@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	// configurações dos clientes para acessar a API
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			   .withClient("angular").secret("@ngul@r0").scopes("read", "write")
			   .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(3600)
			   .refreshTokenValiditySeconds(3600 * 24).and().withClient("flutter").secret("m0b1l30").scopes("read")
			   .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(3600)
			   .refreshTokenValiditySeconds(3600 * 24);

	}

	// configuraçoes de autorização dos endpoints
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(tokenEnhancerChain), accessTokenConverter()));

		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancerChain)
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);

	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("gerenciador_despesas");
		return accessTokenConverter;
	}

	// bean Responsável por retornar um tokenStore
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public TokenEnhancer tokenEnhancer(TokenEnhancerChain tokenEnhancerChain) {
		return new CustomTokenEnhancer();
	}
}
