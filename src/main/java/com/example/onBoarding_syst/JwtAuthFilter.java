package com.example.onBoarding_syst;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.onBoarding_syst.controller.JwtService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			
			String token = authHeader.substring(7);
			
			try {
				
				String username = jwtService.extractUsername(token);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(() -> "ROLE_USER"));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			catch (JwtException ex) {
				
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
