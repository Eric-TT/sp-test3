package com.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.util.HttpClientService;
import com.db.util.ObjectMapperUtil;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	HttpClientService httpClient;

	@Override
	public Item findItemById(Long itemId) {
		String url = "http://manage.db.com//web/item/findItemById/" + itemId;
		String item = httpClient.doGet(url);
		return ObjectMapperUtil.toObject(item, Item.class);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.db.com//web/item/findItemDescById/" + itemId;
		String itemDesc = httpClient.doGet(url);
		return ObjectMapperUtil.toObject(itemDesc, ItemDesc.class);
	}
}
