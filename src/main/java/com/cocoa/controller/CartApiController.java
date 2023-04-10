package com.cocoa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocoa.common.api.CommonResult;
import com.cocoa.model.CartItemParam;
import com.cocoa.service.CartService;
import com.cocoa.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product/api")
public class CartApiController {
	@Autowired CartService cartService;
	
	@Autowired UserService userService;
	
	@ApiOperation("增加购物车商品数量")
	@PutMapping("increment")
	public CommonResult incrementByone(@RequestBody CartItemParam cartItemParam, Principal principal) {
		cartItemParam.setMemberId(userService.getIdByName(principal.getName()));
		cartService.increQuantityBy(cartItemParam);
		return CommonResult.success(null);
	}
	
	@ApiOperation("减少购物车商品数量")
	@PutMapping("decrement")
	public CommonResult decrementByone(@RequestBody CartItemParam cartItemParam, Principal principal) {
		cartItemParam.setMemberId(userService.getIdByName(principal.getName()));
		cartService.decreQuantity(cartItemParam);
		return CommonResult.success(null);
	}
}
