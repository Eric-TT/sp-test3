package com.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.service.ItemService;
import com.db.vo.EasyUI_Table;
import com.db.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

//	@Cache_Find(value = "Item_Page_")
	@RequestMapping("/query")
	public EasyUI_Table<Item> findItemByPage(Integer page, Integer rows) {
		return itemService.findItemByPage(page, rows);
	}

	@RequestMapping("/save")
//	@Cache_Update(value = "Item_Page_")
	public SysResult saveItem(Item item, ItemDesc itemDesc) {
		itemService.saveItem(item, itemDesc);
		return SysResult.success();
	}

	@RequestMapping("/update")
//	@Cache_Update(value = "Item_Page_")
	public SysResult updateItem(Item item, ItemDesc itemDesc) {
		itemService.updateItem(item, itemDesc);
		return SysResult.success();
	}

	@RequestMapping("/query/item/desc/{descId}")
//	@Cache_Update
	public SysResult findItemDescById(@PathVariable Long descId) {
		ItemDesc itemDesc = itemService.findItemDescById(descId);
		return SysResult.success(itemDesc);
	}

	@RequestMapping("/delete")
//	@Cache_Update(value = "Item_Page_")
	public SysResult deleteItems(Long... ids) {
		itemService.deleteItems(ids);
		return SysResult.success();
	}

	@RequestMapping("/instock")
//	@Cache_Update(value = "Item_Page_")
	public SysResult instockItems(Long... ids) {
		int status = 2; // 表示下架
		itemService.changeStatus(status, ids);
		return SysResult.success();
	}

	@RequestMapping("/reshelf")
//	@Cache_Update(value = "Item_Page_")
	public SysResult reshelfItems(Long... ids) {
		int status = 1; // 表示上架
		itemService.changeStatus(status, ids);
		return SysResult.success();
	}

}
