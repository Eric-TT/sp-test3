package com.db.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Reference(timeout = 5000, check = false)
	private DubboItemService itemService;

	@RequestMapping("/{itemId}")
	public String findItemById(@PathVariable Long itemId, Model model) {
		Item item = itemService.findItemById(itemId);
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		Map<String, Object> map = new HashMap<>();
		map.put("item", item);
		map.put("itemDesc", itemDesc);
		model.addAllAttributes(map);
		return "item";
	}
}
