package com.db.service;

import java.util.List;

import com.db.vo.EasyUI_Tree;

public interface ItemCatService {

	String findItemNameById(Long itemCatId);

	List<EasyUI_Tree> findItemCatByParentId(Long id);

	List<EasyUI_Tree> findItemCatByCache(Long parentId);

}
