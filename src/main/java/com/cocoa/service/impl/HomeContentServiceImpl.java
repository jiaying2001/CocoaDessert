package com.cocoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoa.dao.HomeContentDao;
import com.cocoa.model.HomeContent;
import com.cocoa.service.HomeContentService;

@Service
public class HomeContentServiceImpl implements  HomeContentService{

	@Autowired HomeContentDao homeContentDao;
	
	@Override
	public HomeContent content() {
		HomeContent content = new HomeContent();
		content.setNewProductList(homeContentDao.getNewProductList(0,4));
		return content;
	}

}
