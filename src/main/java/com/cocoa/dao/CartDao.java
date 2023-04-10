package com.cocoa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.cocoa.model.CartItemParam;

@Component
public interface CartDao {
	int addToCart(CartItemParam cartItemParam);

	CartItemParam findByProductIdAndMemberId(CartItemParam cartItemParam);

	int increQuantityBy(CartItemParam cartItemParam);

	List<CartItemParam> getCartItemsById(@Param("memberId") long memberId);
}
