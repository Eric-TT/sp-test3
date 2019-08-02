package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Item;

@Controller
public class IndexController {
	@RequestMapping("/page/{moduleName}")
	public String itemAdd(@PathVariable String moduleName) {
		return moduleName;
	}
	
	@RequestMapping("/saveItem/{title}/{sellpoint}/{price}")
	@ResponseBody
	public Item saveItem(Item item) {
		return item;
	}
	/*
	 * 测试负载均衡
	 */
	@RequestMapping("/getMsg")
	@ResponseBody
	public String getMsg() {
		return "我是服务器3";
	}
	
}
