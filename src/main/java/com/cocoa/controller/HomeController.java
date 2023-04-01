package com.cocoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocoa.common.api.CommonResult;
import com.cocoa.model.HomeContent;
import com.cocoa.service.HomeContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Api(tags="主页控制器")
@Tag(name="控制器", description="显示主页内容")
@RequestMapping("/home")
public class HomeController {
	
	@Autowired HomeContentService homeContentService;
	
	@ApiOperation("主页内容")
	@GetMapping("/content")
	public String homeContent(Model model) {
		HomeContent content = homeContentService.content();
		model.addAttribute("newProducts", content.getNewProductList());
		return "home/index";
	}
}
