package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUI_Table;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	public EasyUI_Table<Item> findItemByPage(Integer page,Integer rows) {
		EasyUI_Table<Item> result = itemService.findItemByPage(page,rows);
		return result;
	}
	/* item/save */
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		/*
		 * try { itemService.saveItem(item); return SysResult.success(); } catch
		 * (Exception e) { e.printStackTrace(); return SysResult.fail(); }
		 */
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
	/* /item/update 商品的修改*/
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	/* 删除商品 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids) {
		itemService.deleteItems(ids);
		return SysResult.success();
	}
	/* item/instock */
	/**
	 * 商品的下架
	 */
	@RequestMapping("/instock")
	public SysResult itemInstock(Long[] ids) {
		int status=2;
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}

	/* 商品上架 */
	@RequestMapping("/reshelf")
	public SysResult itemReshelf(Long[] ids) {
		int status=1;
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		ItemDesc itemDesc= itemService.findItemDescById(itemId);
		return SysResult.success(itemDesc);
	}
}
