package com.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.db.pojo.ItemParam;
import com.db.service.ItemParamService;
import com.db.vo.EasyUI_Table;

@RestControllerAdvice
@RequestMapping("/item/param/")
public class ItemParamController {

	@Autowired
	ItemParamService itemParamService;

	@RequestMapping("list")
//	@Cache_Find("Item_Param_Page_")
	public EasyUI_Table<ItemParam> findItemParamByPage(Integer page, Integer rows) {
		return itemParamService.findItemParamByPage(page, rows);
	}
}
