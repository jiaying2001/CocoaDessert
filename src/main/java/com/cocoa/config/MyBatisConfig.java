package com.cocoa.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cocoa.dao")
public class MyBatisConfig {
	@Bean
	@Autowired
	public SqlSessionFactory getSqlSessionFactory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(datasource);
        return factoryBean.getObject();
	}
}
