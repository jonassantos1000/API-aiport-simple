package com.magna.aeroporto.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("test")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	//Método responsavel por lidar com a parte de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and().headers().frameOptions().sameOrigin()
		.and().csrf().disable();
	}
} 
