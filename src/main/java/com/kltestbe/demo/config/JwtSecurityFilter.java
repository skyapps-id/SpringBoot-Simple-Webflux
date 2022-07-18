package com.kltestbe.demo.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kltestbe.demo.auth.CustomUserDetails;
import com.kltestbe.demo.auth.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@ Component
public class JwtSecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtManager jwtManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// get only token if the token is in the form of "Bearer token"
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtManager.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.info("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// validate the token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtManager.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
