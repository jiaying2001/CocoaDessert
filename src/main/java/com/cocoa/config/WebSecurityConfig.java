package com.cocoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cocoa.security.BearerAccessDeniedHandler;
import com.cocoa.security.BearerAuthenticationEntryPoint;
import com.cocoa.security.JwtRequestFilter;
import com.cocoa.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BearerAuthenticationEntryPoint bearerAuthenticationEntryPoint;
	
	@Autowired
	BearerAccessDeniedHandler bearerAccessDeniedHandler;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 	http.authorizeHttpRequests((authorize) -> authorize.antMatchers("/login", "/home/**", "/product/basket").permitAll()
		 			.regexMatchers("/product/detail\\?productId=\\d*", "/product/addToCart\\?productId=\\d*").permitAll())
		 	.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
		 	.csrf((csrf) -> csrf.disable())
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling((exceptions) -> exceptions
								.authenticationEntryPoint(bearerAuthenticationEntryPoint)
								.accessDeniedHandler(bearerAccessDeniedHandler))
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 	return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailService() {
        return username -> userService.getUserDetailsByUsername(username);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
