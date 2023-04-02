package com.cocoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cocoa.dao.UserDao;
import com.cocoa.model.UserDetailImpl;
import com.cocoa.model.UserInfo;
import com.cocoa.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserDao userDao;
	
	@Override
	public UserDetails getUserDetailsByUsername(String username) {
		UserInfo userInfo = userDao.getUserInfoByUsername(username);
		if(userInfo != null) return new UserDetailImpl(userInfo);
		throw new UsernameNotFoundException("用户名或密码错误");
	}

}
