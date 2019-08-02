package com.db.service;

import com.db.pojo.Item;
import com.db.pojo.ItemDesc;

public interface ItemService {

	ItemDesc findItemDescById(Long descId);

	Item findItemById(Long itemId);

}
