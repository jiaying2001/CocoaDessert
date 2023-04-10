package com.cocoa.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemParam {
	private long productId;
	
	private long memberId;
	
	private Date created;
	
	private Date modified;
}
