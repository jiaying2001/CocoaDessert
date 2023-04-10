package com.cocoa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocoa.common.api.CommonResult;
import com.cocoa.model.CartItemParam;
import com.cocoa.model.UserInfo;
import com.cocoa.service.CartService;
import com.cocoa.service.ProductService;
import com.cocoa.service.UserService;

import io.swagger.annotations.ApiOperation;

@ApiOperation("商品控制器")
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired 
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	CartService cartService;
	
	@ApiOperation("通过id查询商品")
	@GetMapping("/detail")
	public String productDetail(@RequestParam("productId") long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "/product/detail";
	}
	
	@ApiOperation("添加到购物车页面")
	@GetMapping("/addToCart")
	public String addToCart(@RequestParam("productId") long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "/product/addToCart";
	}
	
	@ApiOperation("执行添加到购物车")
	@PostMapping("/addToCartAction")
	@ResponseBody
	public CommonResult addToCartAction(@ModelAttribute CartItemParam cartItemParam, Model model, Principal principal) {
		cartItemParam.setMemberId(userService.getIdByName(principal.getName()));
		cartService.addToCart(cartItemParam);
		return CommonResult.redirect("/product/detail?productId=" + cartItemParam.getProductId());
	}
}
