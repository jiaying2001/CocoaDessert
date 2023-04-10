package com.cocoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoa.dao.ProductDao;
import com.cocoa.model.PmsProduct;
import com.cocoa.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	ProductDao productDao;
	
	@Override
	public PmsProduct findById(long id) {
		return productDao.selectById(id);
	}

}
