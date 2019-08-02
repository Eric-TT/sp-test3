package com.db.service;

import com.db.pojo.Content;
import com.db.vo.EasyUI_Table;
import com.db.vo.SysResult;

public interface ContentService {

	EasyUI_Table<Content> findContentByPage(Long categoryId, Integer page, Integer rows);

	SysResult editContent(Content content);

	SysResult deleteContent(Long[] ids);

	void saveContent(Content content);

}
