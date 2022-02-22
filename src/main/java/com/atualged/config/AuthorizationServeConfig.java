package com.atualged.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServeConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		//oauthServer.allowFormAuthenticationForClients();
	}

	private final static String SCOPE_READ = "read";
	private final static String SCOPE_WHITE = "white";
	private final static String SCOPE_EDIT = "edit";

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) {

		try {
			clients.inMemory().withClient("sampleClientId").authorizedGrantTypes("implicit")
					.scopes(SCOPE_READ, SCOPE_WHITE, SCOPE_EDIT).autoApprove(true).and().withClient("angular").scopes(SCOPE_READ, SCOPE_WHITE, SCOPE_EDIT)
					.secret(encoder.encode("angular"))
					.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes(SCOPE_READ)
					.accessTokenValiditySeconds(1800 * 4).refreshTokenValiditySeconds(1800 * 24).and()
					.withClient("mobile")
					.secret(encoder.encode("angular"))
					.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("read")
					.accessTokenValiditySeconds(1800 * 4).refreshTokenValiditySeconds(1800 * 24);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(tokenEnhancer(),accessTokenConverter()));
		
		endpoints.tokenStore(tokenStore()).tokenEnhancer(chain).reuseRefreshTokens(true)
				.authenticationManager(authenticationManager);
	}

	private TokenEnhancer tokenEnhancer() {
		// TODO Auto-generated method stub
		return new CustomTokenEnhancer();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		// TODO Auto-generated method stub
		JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		jwt.setSigningKey("vaca");
		return jwt;
	}

	@Bean
	public TokenStore tokenStore() {
		// TODO Auto-generated method stub
		return new JwtTokenStore(accessTokenConverter());
	}
}
