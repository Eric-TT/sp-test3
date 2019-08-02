package com.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.annotation.Cache_Find;
import com.db.enums.TYPE_ENUM;
import com.db.service.ItemCatService;
import com.db.vo.EasyUI_Tree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	ItemCatService itemCatService;

	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		return itemCatService.findItemNameById(itemCatId);
	}

	@Cache_Find(type = TYPE_ENUM.AUTO)
	@RequestMapping("/list")
	public List<EasyUI_Tree> findItemCatByParentId(@RequestParam(defaultValue = "0") Long id) {
		List<EasyUI_Tree> eTree = itemCatService.findItemCatByParentId(id);
		return eTree;
	}

}
