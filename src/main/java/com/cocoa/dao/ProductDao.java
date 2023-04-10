package com.cocoa.dao;

import org.apache.ibatis.annotations.Param;

import com.cocoa.model.PmsProduct;

public interface ProductDao {
	PmsProduct selectById(@Param("id") long id);
}
