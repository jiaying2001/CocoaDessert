package com.cocoa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocoa.common.api.CommonResult;
import com.cocoa.common.api.ResultCode;
import com.cocoa.model.UmsAdminLoginParam;
import com.cocoa.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping()
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
	
	@ApiOperation(value = "登录以后返回token")
	@PostMapping("/login")
	@ResponseBody
	public CommonResult login(@ModelAttribute UmsAdminLoginParam umsAdminLoginParam, 
			BindingResult result,
			HttpServletResponse response) {
		String token = userService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
		if(token == null) {
			return CommonResult.failed(ResultCode.FAILED,"用户名或密码错误");
		}
		response.addCookie(new Cookie("jwt-token", token));
		return CommonResult.success("", "登入成功");
	}
}
