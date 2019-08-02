package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;


@Controller
@RequestMapping("/user")
public class UserController {
	/**
	 * 利用dubbo注解
	 */
	@Reference(timeout = 3000,check = false)
	private DubboUserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/{moduleName}")
	public String moduleName(@PathVariable String moduleName) {
		return moduleName;
	}
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		userService.saveUser(user);
		return SysResult.success(user.getUsername());
	}
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response) {
		String token=userService.doLogin(user);
		//校验远程返回的数据是否为null
		if (StringUtils.isEmpty(token)) {
			return SysResult.fail();
		}
		//将token写入cookie中
		Cookie cookie = new Cookie("JT_TICKET", token);
		cookie.setMaxAge(7*24*3600);
		cookie.setPath("/");
		cookie.setDomain("jt.com");
		response.addCookie(cookie);
		return SysResult.success();
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
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
		
		if (!StringUtils.isEmpty(token)) {
			jedisCluster.del(token);
			Cookie cookie = new Cookie("JT_TICKET", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain("jt.com");
			response.addCookie(cookie);
		}
		return "redirect:/";//重定向系统首页
	}
}
