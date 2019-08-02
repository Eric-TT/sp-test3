package com.db.service;

import com.db.pojo.ItemParam;
import com.db.vo.EasyUI_Table;

public interface ItemParamService {

	EasyUI_Table<ItemParam> findItemParamByPage(Integer page, Integer rows);

	

}
