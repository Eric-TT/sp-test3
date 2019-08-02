package com.db.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.db.pojo.User;
import com.db.service.DubboUserService;
import com.db.util.Token;
import com.db.util.UserThreadLocal;
import com.db.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

	@Reference(timeout = 5000, check = true)
	private DubboUserService userService;

	@Autowired
	private JedisCluster jedis;

	@RequestMapping("/{moduleName}")
	public String moduleName(@PathVariable String moduleName) { // 通用页面跳转
		return moduleName;
	}

	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult doRegister(User user) {
		try {
			int row = userService.doRegister(user);
			if (row == 1) {
				return SysResult.success(user.getUsername());
			} else {
				return SysResult.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}

	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user, HttpServletResponse response) {
		String token = userService.doLogin(user);
		if (StringUtils.isEmpty(token)) {
			return SysResult.fail();
		}
		Cookie cookie = new Cookie("DB_TICKET", token);
		cookie.setMaxAge(7 * 24 * 3600);
		cookie.setPath("/");
		cookie.setDomain("db.com");
		response.addCookie(cookie);
		return SysResult.success();
	}

	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Cookie[] cookies = request.getCookies();
		String ticket = "DB_TICKET";
		String token = Token.getToken(cookies, ticket);
		if (!StringUtils.isEmpty(token)) {
			jedis.del(token);
			Cookie cook = new Cookie(ticket, "");
			cook.setMaxAge(0);
			cook.setPath("/");
			cook.setDomain("db.com");
			response.addCookie(cook);
		}
		return "redirect:/";
	}

	@RequestMapping("/my-info")
	public String doMyinfo(Model model) {
		Long userId = UserThreadLocal.get().getId();
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		return "my-info";
	}
}
