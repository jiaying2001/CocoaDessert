package com.cocoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cocoa.common.util.JwtTokenUtil;
import com.cocoa.dao.UserDao;
import com.cocoa.model.UserDetailImpl;
import com.cocoa.model.UserInfo;
import com.cocoa.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserDao userDao;
	
	@Autowired JwtTokenUtil jwtTokenUtil;
	
	@Override 
	public UserDetails getUserDetailsByUsername(String username) {
		UserInfo userInfo = userDao.getUserInfoByUsername(username);
		if(userInfo != null) return new UserDetailImpl(userInfo);
		throw new UsernameNotFoundException("用户名或密码错误");
	}
	
	@Override
	public String login(String username, String password) {
		UserDetails user = getUserDetailsByUsername(username);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(password, user.getPassword())) {
			return null;
		}
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtTokenUtil.generateToken(user);
	}

	@Override
	public long getIdByName(String username) {
		return userDao.getIdByName(username);
	}
}
