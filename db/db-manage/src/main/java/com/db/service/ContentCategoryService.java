package com.db.service;

import java.util.List;

import com.db.vo.EasyUI_Tree;

public interface ContentCategoryService {

	List<EasyUI_Tree> findContentCategoryByParentId(Long parentId);

}
