package com.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.ContentCategoryMapper;
import com.db.pojo.ContentCategory;
import com.db.vo.EasyUI_Tree;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUI_Tree> findContentCategoryByParentId(Long parentId) {
		List<EasyUI_Tree> treeList = new ArrayList<>();
		// 1.获取数据库数据
		List<ContentCategory> contentCategoryList = findContentCategoryList(parentId);
		System.out.println(contentCategoryList);

		for (ContentCategory contentCategory : contentCategoryList) {
			Long id = contentCategory.getId();
			String text = contentCategory.getName();
			// 一级二级菜单 closed 三级菜单 open
			String state = contentCategory.getIsParent() ? "closed" : "open";
			EasyUI_Tree tree = new EasyUI_Tree(id, text, state);
			treeList.add(tree);
		}
		return treeList;
	}

	public List<ContentCategory> findContentCategoryList(Long parentId) {
		QueryWrapper<ContentCategory> queryWrapper = new QueryWrapper<ContentCategory>();
		queryWrapper.eq("parent_id", parentId);
		return contentCategoryMapper.selectList(queryWrapper);
	}
}
