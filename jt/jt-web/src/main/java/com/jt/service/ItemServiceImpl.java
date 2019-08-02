package com.jt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientServer;
import com.jt.util.ObjectMapperUtil;
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private HttpClientServer HttpClient;
	@Override
	public Item findItemById(long itemId) {
		String url = "http://manage.jt.com/web/item/findItemById/"+itemId;
		String itemJSON = HttpClient.doGet(url);
		return ObjectMapperUtil.toObject(itemJSON,Item.class);

	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemDescById/"+itemId;
		String itemDescJSON = HttpClient.doGet(url);
		return ObjectMapperUtil.toObject(itemDescJSON, ItemDesc.class);

	}

}
