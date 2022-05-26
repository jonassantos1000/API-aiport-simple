package com.magna.aeroporto.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.magna.aeroporto.entities.Users;
import com.magna.aeroporto.service.UsersService;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsersService userService;
	
	public AuthenticationTokenFilter(TokenService tokenService, UsersService userService) {
		this.tokenService=tokenService;
		this.userService= userService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valid = tokenService.isTokenValid(token);
		if(valid) authUser(token);
		
		filterChain.doFilter(request, response);
	}
	
	private void authUser(String token) {
		Long idUser = tokenService.getIdUser(token);
		Users user = userService.findById(idUser);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	} 

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
}
