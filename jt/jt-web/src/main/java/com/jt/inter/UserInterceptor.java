package com.jt.inter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;

import redis.clients.jedis.JedisCluster;
@Component
public class UserInterceptor implements HandlerInterceptor{
	@Autowired
	private JedisCluster jedisCluster;
	/*
	 * boolen:
	 * true:表示放行
	 * false:表示拦截  一般配合重定向使用
	 * 
	 * 业务实现步骤
	 * 1.获取用户cookie中的token信息
	 * 2.校验数据是否有效
	 * 3.校验redis中是否有数据
	 * 如果上述无误返回true
	 * 否则reture false返回页面重定向
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取cookie数据
		Cookie[] cookies = request.getCookies();
		String token=null;
		if (cookies.length>0) {
			for (Cookie cookie : cookies) {
				if ("JT_TICKET".equals(cookie.getName())) {
					token=cookie.getValue();
					break;
				}
			}
		}
		//2.校验token的有效性
		if (!StringUtils.isEmpty(token)) {
			String userJSON=jedisCluster.get(token);
			if (!StringUtils.isEmpty(userJSON)) {
				User user=ObjectMapperUtil.toObject(userJSON,User.class);
				request.setAttribute("JT_USER", user);
				UserThreadLocal.set(user);
				return true;
			}
		}
		response.sendRedirect("/user/login.html");
		return false;
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserThreadLocal.remove();
	}
}
