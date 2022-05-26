package com.magna.aeroporto.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.aeroporto.config.security.TokenService;
import com.magna.aeroporto.dto.AuthenticationDTO;
import com.magna.aeroporto.dto.TokenDTO;

@RestController
@RequestMapping(value = "/auth")
@Profile("prod")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> authentication (@RequestBody @Valid AuthenticationDTO auth){
		UsernamePasswordAuthenticationToken login = auth.converter();
		Authentication authentication = manager.authenticate(login);
		String token = tokenService.gerarToken(authentication);
		return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
	}
}
