package com.teastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teastore.interceptor.AuthorizeInterceptor;
import com.teastore.interceptor.ShareInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
	
	@Autowired
	ShareInterceptor share;
	
	@Autowired
	AuthorizeInterceptor auth;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(share).addPathPatterns("/**");
		registry.addInterceptor(auth)
			.addPathPatterns("/account/change", "/account/logoff","/account/edit", "/order/**");
	}
}
