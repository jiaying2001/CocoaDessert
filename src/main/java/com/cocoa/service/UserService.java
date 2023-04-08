package com.cocoa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
	UserDetails getUserDetailsByUsername(String username);

	String login(String username, String password);
}
