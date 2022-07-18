package com.kltestbe.demo.config;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kltestbe.demo.auth.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private JwtSecurityFilter jwtSecurityFilter;

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests(configurer -> configurer.antMatchers("/users/signup", "/users/login").permitAll()
				.anyRequest().authenticated())
			.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
			.authenticationEntryPoint((request, response, ex) -> {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
			});
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
