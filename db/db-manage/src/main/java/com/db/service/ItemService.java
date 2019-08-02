package com.db.service;

import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.vo.EasyUI_Table;

public interface ItemService {

	EasyUI_Table<Item> findItemByPage(Integer page, Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItems(Long... ids);

	void changeStatus(Integer status, Long... ids);

	ItemDesc findItemDescById(Long descId);

	Item findItemById(Long itemId);

}
