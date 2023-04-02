package com.cocoa.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	UserDetails getUserDetailsByUsername(String username);
}
