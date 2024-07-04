package com.hackathon.TimeLapse.oauth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		log.info("[*] Jwt Filter");
		String token = resolveToken(request);
		log.info("[*] Jwt token >>>>> " + token);
		if (token != null) {
			Authentication authentication = getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContextHolder에 담기
		}
		chain.doFilter(request, response);
	}
	private Authentication getAuthentication(String token) {
		log.info("[*] subject: "+jwtTokenProvider.extractSubject(token));
		return new UsernamePasswordAuthenticationToken(jwtTokenProvider.extractSubject(token), null,
			Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")));
	}
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.split(" ")[1];
		}
		return null;
	}
}
