package com.db.shiro;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class WebFilterConfig {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean newFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.setOrder(Integer.MAX_VALUE - 1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}