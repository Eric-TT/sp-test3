package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUI_Table;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUI_Table<Item> findItemByPage(Integer page, Integer rows) {
		//获取总记录数
		int total =itemMapper.selectCount(null);
		//分页查询list集合
		int start=(page-1)*rows;
		List<Item> items=itemMapper.findItemByPage(start,rows);
		return new EasyUI_Table<Item>(total, items);
	}
	@Transactional //添加事务控制
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}
	@Transactional //添加事务控制
	@Override
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}
	@Transactional //添加事务控制
	@Override
	public void deleteItems(Long[] ids) {
		//1.手写sql
		/* itemMapper.deleteItems(ids); */
		List<Long> adList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(adList);
	}
	@Transactional //添加事务控制
	@Override
	public void updateStatus(Long[] ids, int status) {
		Item item =new Item();
		item.setStatus(status).setUpdated(new Date());
		List<Long> longList = Arrays.asList(ids);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", longList);
		itemMapper.update(item, updateWrapper);
		
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}
	@Override
	public Item findItemById(long itemId) {
		System.out.println("查询数据库");
		return itemMapper.selectById(itemId);
	}

}
