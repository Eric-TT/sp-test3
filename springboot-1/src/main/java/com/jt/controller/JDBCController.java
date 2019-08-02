package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.JDBCConnectionService;
import com.jt.service.JDBCServiceB;
import com.jt.service.JDBCServiceC;
import com.jt.service.drConnectionService;

//@Controller+@ResponseBody
@RestController
public class JDBCController {
	@Autowired
	private JDBCConnectionService jdbcService;
	@RequestMapping("/jdbcTest")
	public JDBCConnectionService jdbcTest() {
		System.out.println(jdbcService);
		return jdbcService;
	}
	@Autowired
	private drConnectionService drService;
	@RequestMapping("/dr")
	public drConnectionService dr() {
		return drService;
	}
	@Autowired
	private JDBCServiceB jdbcServiceB;
	//测试批量赋值的操作
	@RequestMapping("/jdbcTestB")
	public JDBCServiceB jdbcTestB() {
		return jdbcServiceB;
	}
	//测试自定义配置文件
	@Autowired
	private JDBCServiceC jdbcServiceC;
	@RequestMapping("/testJDBCC")
	public JDBCServiceC testJDBCC() {
		return jdbcServiceC;
	}
}
