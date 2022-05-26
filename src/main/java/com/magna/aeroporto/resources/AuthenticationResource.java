package com.magna.aeroporto.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.aeroporto.config.security.TokenService;
import com.magna.aeroporto.config.validacao.Users;
import com.magna.aeroporto.dto.AuthenticationDTO;
import com.magna.aeroporto.service.UsersService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsersService service;
	
	@PostMapping
	public ResponseEntity<?> authentication (@RequestBody @Valid AuthenticationDTO auth){
		Users user = new Users();
		user.setEmail(auth.getEmail());
		user.setPassword("$2a$10$OutjCfvo0RlNLUw8Ji3IMeEcG1j7JP7/Ycjg26XnRcqJSuQCk1MN.");
		service.insert(user);
		
		UsernamePasswordAuthenticationToken login = auth.converter();
		Authentication authentication = manager.authenticate(login);
		String token = tokenService.gerarToken(authentication);
		System.out.println("token: " + token);
		return ResponseEntity.ok().build();
	}
}
