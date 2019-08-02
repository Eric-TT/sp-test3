package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUI_Tree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/queryItemName")
	public String findItemCatById(Long itemCatId) {
		return itemCatService.findItemCatNameById(itemCatId);
	}
	/**
	 *实现商品树形结构的查询 
	 * 
	 */
	@RequestMapping("/list")
	public List<EasyUI_Tree> findItemCatByParentId(@RequestParam(name = "id",defaultValue = "0")Long parentId){
		//Long parentId=id==null?0l:id;
//		return itemCatService.findItemCatByCache(parentId);
		return itemCatService.findItemCatByParentId(parentId);
	}
	
}
