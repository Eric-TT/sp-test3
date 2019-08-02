package com.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.ItemCatMapper;
import com.db.pojo.ItemCat;
import com.db.util.ObjectMapperUtil;
import com.db.vo.EasyUI_Tree;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private JedisCluster jedis;

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public String findItemNameById(Long itemCatId) {
		ItemCat bean = itemCatMapper.selectById(itemCatId);
		return bean.getName();
	}

	@Override
	public List<EasyUI_Tree> findItemCatByParentId(Long pid) {
		List<EasyUI_Tree> list = new ArrayList<>();
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", pid);
		List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
		for (ItemCat itemCat : itemCatList) {
			Long id = itemCat.getId();
			String text = itemCat.getName();
			String state = itemCat.getIsParent() ? "closed" : "open";
			EasyUI_Tree eTree = new EasyUI_Tree(id, text, state);
			list.add(eTree);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUI_Tree> findItemCatByCache(Long parentId) {
		String key = "ITEM_CAT_" + parentId;
		String result = jedis.get(key);
		List<EasyUI_Tree> list = new ArrayList<>();
		if (StringUtils.isEmpty(result)) {
			System.out.println("查数据库");
			list = findItemCatByParentId(parentId);
			String json = ObjectMapperUtil.toJSON(list);
			jedis.set(key, json);
		} else {
			System.out.println("查缓存");
			list = ObjectMapperUtil.toObject(result, list.getClass());
		}
		return list;
	}

}
