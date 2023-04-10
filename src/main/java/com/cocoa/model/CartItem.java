package com.cocoa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
	private PmsProduct product;
	
	private CartItemParam cartItemParam;
}
