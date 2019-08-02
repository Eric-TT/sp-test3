package com.db.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.mapper.ContentMapper;
import com.db.pojo.Content;
import com.db.vo.EasyUI_Table;
import com.db.vo.SysResult;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;

	@Override
	public EasyUI_Table<Content> findContentByPage(Long categoryId, Integer page, Integer rows) {
		// 1.查询总记录数
		int total = contentMapper.selectCount(null);
		// 2.分页查询
		int start = (page - 1) * rows;
		List<Content> contentList = contentMapper.findContent(start, rows);
		return new EasyUI_Table<Content>(total, contentList);
	}

	@Override
	public SysResult editContent(Content content) {
		content.setUpdated(new Date());
		contentMapper.updateById(content);
		return SysResult.success(content);
	}

	@Override
	public SysResult deleteContent(Long[] ids) {
		List<Long> idsList = Arrays.asList(ids);
		contentMapper.deleteBatchIds(idsList);
		return SysResult.success();
	}

	@Override
	public void saveContent(Content content) {
		content.setCreated(new Date()).setUpdated(new Date());
		contentMapper.insert(content);

	}

}
