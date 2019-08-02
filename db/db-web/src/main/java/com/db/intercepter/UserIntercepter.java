package com.db.intercepter;

import com.db.pojo.User;
import com.db.util.ObjectMapperUtil;
import com.db.util.Token;
import com.db.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserIntercepter implements HandlerInterceptor {

	@Autowired
	JedisCluster jedis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = "DB_TICKET";
		Cookie[] cookies = request.getCookies();
		String token = Token.getToken(cookies, ticket);
		if (!StringUtils.isEmpty(token)) {
			String userStr = jedis.get(token);
			if (!StringUtils.isEmpty(userStr)) {
				User user = ObjectMapperUtil.toObject(userStr, User.class);
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
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
