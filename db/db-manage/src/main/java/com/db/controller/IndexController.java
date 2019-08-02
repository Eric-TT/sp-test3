package com.db.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.pojo.Item;
import com.db.vo.SysResult;

@Controller
public class IndexController {

	@RequestMapping("/page/{moduleName}")
	public String itemAdd(@PathVariable String moduleName) {
		return moduleName;
	}

	@RequestMapping("/saveItem/{title}/{sellPoint}/{price}")
	@ResponseBody
	public Item saveItem(@PathVariable String title, @PathVariable String sellPoint, @PathVariable Long price) {
		Item item = new Item();
		item.setTitle(title).setSellPoint(sellPoint).setPrice(price);
		return item;
	}

	@RequestMapping("/saveItems/{title}/{sellPoint}/{price}")
	@ResponseBody
	public Item saveItems(Item item) {
		return item;
	}

	@RequestMapping("/doLoginUI")
	public String dologinUI() {
		return "login";
	}

	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLongin(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return SysResult.success();
	}

}
