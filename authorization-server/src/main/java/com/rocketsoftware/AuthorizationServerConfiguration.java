	package com.rocketsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
{






	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("mvisdemoclient")
				.authorizedGrantTypes("password","authorization_code","client_credentials")
				.secret(encoder().encode("secret"))
				.scopes("user_info","read")
				.redirectUris("https://localhost:8443/clientapp/login/oauth2/code/mvisdemoclient")
				.autoApprove(false)
							;
	}








	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.authenticationManager(authenticationManager)
				.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter());
		;
	}




	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer
				.tokenKeyAccess("permitAll")
        	 .checkTokenAccess("isAuthenticated()")
            ;

    }












	//TODO-7  Uncomment the below to enable JWT tokens



	 @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        //converter.setSigningKey("123");
	        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
	        		new ClassPathResource("mykeystore.jks"), "password".toCharArray());
	         converter.setKeyPair(keyStoreKeyFactory.getKeyPair("tomcat"));
	        return converter;
	    }




	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
