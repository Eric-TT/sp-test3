package com.db.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.db.mapper.ItemDescMapper;
import com.db.mapper.ItemMapper;
import com.db.pojo.Item;
import com.db.pojo.ItemDesc;
import com.db.vo.EasyUI_Table;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUI_Table<Item> findItemByPage(Integer page, Integer rows) {
		int total = itemMapper.selectCount(null);
		int start = (page - 1) * rows;
		List<Item> list = itemMapper.findItemByPage(start, rows);
		return new EasyUI_Table<Item>(total, list);
	}

	@Transactional
	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public ItemDesc findItemDescById(Long descId) {

		return itemDescMapper.selectById(descId);
	}

	@Transactional
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
		System.out.println(item);
		item.setUpdated(new Date());
		itemMapper.updateById(item);

		item = itemMapper.selectById(item.getId());
		itemDescMapper.deleteById(item.getId());
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}

	@Transactional
	@Override
	public void deleteItems(Long... ids) {
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
	}

	@Transactional
	@Override
	public void changeStatus(Integer status, Long... ids) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		List<Long> idList = Arrays.asList(ids);
		updateWrapper.in("id", idList);
		itemMapper.update(item, updateWrapper);
	}

	@Override
	public Item findItemById(Long itemId) {
		System.out.println("查询数据库");
		return itemMapper.selectById(itemId);
	}
}
