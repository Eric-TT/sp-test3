package com.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.UserService;
import com.db.vo.SysResult;
import com.fasterxml.jackson.databind.util.JSONPObject;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	JedisCluster jedis;

	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback) {
		try {
			Boolean flag = userService.findCheckUser(param, type);
			return new JSONPObject(callback, SysResult.success(flag));
		} catch (Throwable t) {
			t.printStackTrace();
			return new JSONPObject(callback, SysResult.fail());
		}
	}

	@RequestMapping("/query/{token}")
	public JSONPObject findUserByToken(String callback, @PathVariable String token) {
		String json = jedis.get(token);
		JSONPObject jsonp = null;
		if (StringUtils.isEmpty(json)) {
			jsonp = new JSONPObject(callback, SysResult.fail());
		} else {
			jsonp = new JSONPObject(callback, SysResult.success(json));
		}
		return jsonp;
	}
}
