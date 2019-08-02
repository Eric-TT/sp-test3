package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.mapper.ItemCatMapper;
import com.db.mapper.ItemParamMapper;
import com.db.pojo.ItemCat;
import com.db.pojo.ItemParam;
import com.db.vo.EasyUI_Table;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	ItemCatMapper itemCatMapper;

	@Autowired
	ItemParamMapper itemParamMapper;

	@Override
	public EasyUI_Table<ItemParam> findItemParamByPage(Integer page, Integer rows) {
		int total = itemParamMapper.selectCount(null);
		int start = (page - 1) * rows;
		List<ItemParam> list = itemParamMapper.findItemParamByPage(start, rows);
		for (ItemParam itemParam : list) {
			ItemCat itemCat = itemCatMapper.selectById(itemParam.getItemCatId());
			itemParam.setItemCatName(itemCat.getName());
		}
		return new EasyUI_Table<>(total, list);
	}

}
