package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUI_Tree;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private JedisCluster jedisCluster;
	/*
	 * @Autowired private Jedis jedis;
	 */

	/*
	 * @Autowired private ShardedJedis jedis;//分片操作
	 */
	/*
	 * @Autowired private Jedis jedis;//单台操作
	 */
	@Override
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}

	@Override
	public List<EasyUI_Tree> findItemCatByParentId(Long parentId) {
		ArrayList<EasyUI_Tree> treeList = new ArrayList<>();
		//获取数据库数据
		List<ItemCat> itemCatList=findItemCatList(parentId);
		for (ItemCat itemCat : itemCatList) {
			Long id=itemCat.getId();
			String text=itemCat.getName();
			String state=itemCat.getIsParent()?"closed":"open";
			EasyUI_Tree tree=new EasyUI_Tree(id,text,state);
			treeList.add(tree);
		}
		return treeList;
	}

	private List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUI_Tree> findItemCatByCache(Long parentId) {
		List<EasyUI_Tree> treeList=new ArrayList<>();
		String key="ITEM_CAT_"+parentId;
		String result=jedisCluster.get(key);
		if (StringUtils.isEmpty(result)) {
			treeList=findItemCatByParentId(parentId);
			String json = ObjectMapperUtil.toJson(treeList);
			jedisCluster.set(key,json);
			System.out.println("数据库中取");
		} else {
			treeList=ObjectMapperUtil.toObject(result, treeList.getClass());
			System.out.println("缓存中取");
		}
		return treeList;
	}
	
}
