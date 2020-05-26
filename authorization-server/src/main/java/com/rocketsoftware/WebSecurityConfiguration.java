package com.rocketsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {




	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("demo").password(passwordEncoder.encode("secret"))
			.roles("USER")
		.and()
		.withUser("user1").password(passwordEncoder.encode("secret"))
				.roles("ADMIN")
				.and()
				.withUser("user2").password(passwordEncoder.encode("secret"))
				.roles("USER")
				.and()
				.withUser("admin").password(passwordEncoder.encode("secret"))
				.roles("ADMIN");
	}



	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http
		.authorizeRequests()
		.antMatchers("/login").permitAll()

		.antMatchers("/oauth/authorize")
			.authenticated()
			.and().formLogin()
		.and().requestMatchers()
        	.antMatchers("/login","/oauth/authorize");


	}












}
