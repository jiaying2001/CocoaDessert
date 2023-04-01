package com.cocoa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/"
	};
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        if (!registry.hasMappingForPattern("/webjars/**")) {
	            registry.addResourceHandler("/webjars/**").addResourceLocations(
	                    "classpath:/META-INF/resources/webjars/");
	        }
	        if (!registry.hasMappingForPattern("/**")) {
	            registry.addResourceHandler("/**").addResourceLocations(
	                    CLASSPATH_RESOURCE_LOCATIONS);
	        }
	 
	    }
}
