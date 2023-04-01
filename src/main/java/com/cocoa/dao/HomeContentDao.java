package com.cocoa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cocoa.model.PmsProduct;

public interface HomeContentDao {
	/**
     * 获取新品推荐
     */
    List<PmsProduct> getNewProductList(@Param("base") Integer base,@Param("offset") Integer offset);
}
