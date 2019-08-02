package com.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.pojo.Content;
import com.db.service.ContentService;
import com.db.vo.EasyUI_Table;
import com.db.vo.SysResult;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody

	public EasyUI_Table<Content> findItemByPage(Long categoryId, Integer page, Integer rows) {
		return contentService.findContentByPage(categoryId, page, rows);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public SysResult editContent(Content content) {
		return contentService.editContent(content);

	}

	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteContent(Long[] ids) {
		return contentService.deleteContent(ids);
	}

	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveContent(Content content) {
		contentService.saveContent(content);
		return SysResult.success(content);
	}
}
