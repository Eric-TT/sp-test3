package com.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.service.ContentCategoryService;
import com.db.vo.EasyUI_Tree;

@Controller
@RequestMapping("/content")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/category/list")
	@ResponseBody
	public List<EasyUI_Tree> findContentCategoryByParentId(
			@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		List<EasyUI_Tree> contentCategory = contentCategoryService.findContentCategoryByParentId(parentId);
		return contentCategory;

	}
}
