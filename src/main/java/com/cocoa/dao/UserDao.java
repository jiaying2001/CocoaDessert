package com.cocoa.dao;

import org.apache.ibatis.annotations.Param;

import com.cocoa.model.UserInfo;

public interface UserDao {
	// Get the user credential
	UserInfo getUserInfoByUsername(@Param("username") String username);
	
	long getIdByName(@Param("username") String username);
}
