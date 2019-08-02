package com.db.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.db.intercepter.UserIntercepter;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

	@Autowired
	UserIntercepter userInterceptor;

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

		configurer.setUseSuffixPatternMatch(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(userInterceptor).addPathPatterns("/cart/**", "/order/**","/user/my-info**");
	}
}
