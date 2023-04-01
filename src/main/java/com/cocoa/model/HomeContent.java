package com.cocoa.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("主页内容信息")
public class HomeContent {
	@ApiModelProperty("新品推荐")
    private List<PmsProduct> newProductList;
}
