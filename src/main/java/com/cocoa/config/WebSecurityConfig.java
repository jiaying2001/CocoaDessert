package com.cocoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cocoa.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserService userService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 	http.formLogin((form -> form
					.loginPage("/login")
					.permitAll()))
		 	.authorizeRequests()
		 	.anyRequest().authenticated();
//		 	.antMatchers("/home/content").permitAll()
//		 	.anyRequest().denyAll();
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
