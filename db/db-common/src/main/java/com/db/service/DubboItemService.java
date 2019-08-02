package com.db.service;

import java.util.List;

import com.db.pojo.Item;
import com.db.pojo.ItemDesc;

public interface DubboItemService {

	Item findItemById(Long itemId);

	ItemDesc findItemDescById(Long itemId);

	List<Item> findItemByName(String q);

	List<Item> selectNameByCid(String cid);

}
