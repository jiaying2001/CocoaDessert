package com.cocoa.service;


import java.util.List;

import com.cocoa.model.CartItemParam;

public interface CartService {
	void addToCart(CartItemParam cartItemParam);
	List<CartItemParam> getCartItemsById(long id);
}
