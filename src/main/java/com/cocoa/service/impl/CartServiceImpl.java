package com.cocoa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoa.dao.CartDao;
import com.cocoa.model.CartItem;
import com.cocoa.model.CartItemParam;
import com.cocoa.model.PmsProduct;
import com.cocoa.service.CartService;

import cn.hutool.core.date.DateUtil;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;
	
	@Override
	public void addToCart(CartItemParam cartItemParam) {
		if(existInCart(cartItemParam)) increQuantityBy(cartItemParam);
		else cartDao.addToCart(cartItemParam);
	}

	private boolean existInCart(CartItemParam cartItemParam) {
		CartItemParam cartItem = cartDao.findByProductIdAndMemberId(cartItemParam);
		return (cartItem == null)? false: true; 
	}

	@Override
	public void increQuantityBy(CartItemParam cartItemParam) {
		cartDao.increQuantityBy(cartItemParam);
	}
	
	@Override 
	public void decreQuantity(CartItemParam cartItemParam) {
		cartDao.decreQuantity(cartItemParam);
	}

	@Override
	public List<CartItem> getCartItemsById(long id) {
		return cartDao.getCartItemsById(id);
	}
}
