package com.cocoa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cocoa.common.api.CommonResult;
import com.cocoa.model.HomeContent;
import com.cocoa.service.CartService;
import com.cocoa.service.HomeContentService;
import com.cocoa.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Api(tags="主页控制器")
@Tag(name="控制器", description="显示主页内容")
@RequestMapping("/home")
public class HomeController {
	
	@Autowired HomeContentService homeContentService;
	
	@Autowired CartService cartService;
	
	@Autowired UserService userService;
	
	@ApiOperation("主页内容")
	@GetMapping("/content")
	public String homeContent(Model model) {
		HomeContent content = homeContentService.content();
		model.addAttribute("newProducts", content.getNewProductList());
		return "home/index";
	}
	
	@ApiOperation("我的")
	@GetMapping("/me")
	public String me() {
		return "home/me";
	}
	
	@ApiOperation("购物车")
	@GetMapping("/basket")
	public String basket() {
		return "home/basket";
	}
	
	@ApiOperation("购物车内容")
	@GetMapping("/basketContent")
	public ModelAndView basketContent(Principal principal) {
		ModelAndView mav = new ModelAndView();
		long id = userService.getIdByName(principal.getName());
		mav.setViewName("home/basketContent");
		mav.addObject("cartItems", cartService.getCartItemsById(id));
		return mav;
	}
}
