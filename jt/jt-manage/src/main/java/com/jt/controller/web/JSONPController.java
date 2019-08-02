package com.jt.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemCat;

@RestController	//要求返回json数据
public class JSONPController {
	/*
	 * @RequestMapping("/web/testJSONP") public String testJSONP(String callback) {
	 * ItemCat itemCat=new ItemCat(); itemCat.setId(1000L).setName("json测试"); String
	 * json=ObjectMapperUtil.toJson(itemCat); return callback+"("+json+")"; }
	 */
	@RequestMapping("/web/testJSONP")
	public JSONPObject testJSONP(String callback) {
		ItemCat itemCat=new ItemCat();
		itemCat.setId(100l).setName("优化");
		JSONPObject json=new JSONPObject(callback,itemCat );
		return json;
	}
}
