package com.cocoa.service;


import java.util.List;

import com.cocoa.model.CartItem;
import com.cocoa.model.CartItemParam;

public interface CartService {
	void addToCart(CartItemParam cartItemParam);
	List<CartItem> getCartItemsById(long id);
	void increQuantityBy(CartItemParam cartItemParam);
	void decreQuantity(CartItemParam cartItemParam);
}
