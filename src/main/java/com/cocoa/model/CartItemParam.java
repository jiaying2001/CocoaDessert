package com.cocoa.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemParam {
	private long productId;
	
	private long memberId;
	
	private long quantity;
	
	private Date created;
	
	private Date modified;
}
