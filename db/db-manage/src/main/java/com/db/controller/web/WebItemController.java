package com.db.controller.web;

import com.db.annotation.Cache_Find;
import com.db.enums.TYPE_ENUM;
import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/item")
public class WebItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/findItemById/{itemId}")
	@Cache_Find(type = TYPE_ENUM.AUTO)
	public Item findItemById(@PathVariable Long itemId) {
		return itemService.findItemById(itemId);
	}

	@RequestMapping("/findItemDescById/{itemId}")
	@Cache_Find(type = TYPE_ENUM.AUTO)
	public ItemDesc findItemDescById(@PathVariable Long itemId) {
		return itemService.findItemDescById(itemId);
	}
}
