package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject findCheckUser(String callback,
									@PathVariable String param,
									@PathVariable Integer type
									) {
		JSONPObject jsonpObject=null;
		try {
			//查询数据库检查数据是否存在
			boolean flag =userService.findUserCheck(param,type);
			jsonpObject=new JSONPObject(callback, SysResult.success(flag));
		} catch (Exception e) {
			e.printStackTrace();
			jsonpObject=new JSONPObject(callback, SysResult.success());
		}
		return jsonpObject;
		/*
		 * User user = new User(); if (type==1) { user.setUsername(param); } else if
		 * (type==2) { user.setPhone(param); }else if(type==3){ user.setEmail(param); }
		 * user=userService.findUser(user); JSONPObject json=new
		 * JSONPObject(callback,SysResult.success(user));
		 * System.out.println(SysResult.success(user)); return json;
		 */
	}
	@RequestMapping("/query/{token}")
	public JSONPObject findUserByToken(String callback,
										@PathVariable String token) {
		String userJSON = jedisCluster.get(token);
		JSONPObject jsonpObject=null;
		if (StringUtils.isEmpty(userJSON)) {
			jsonpObject=new JSONPObject(callback, SysResult.fail());
		}else {
			jsonpObject=new JSONPObject(callback, SysResult.success(userJSON));
		}
		return jsonpObject;
	}
}
