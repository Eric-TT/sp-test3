package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.User;
import com.jt.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("findAll")
	public String userList(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "userList";
	}
	@RequestMapping("userList")
	public String ToJSP() {
		return "userList-ajax";
	}
	@RequestMapping("/userList-ajax")
	@ResponseBody
	public List<User> findList_ajax(){
		List<User> userList =userService.findAll();
		return userList;
	}
}
