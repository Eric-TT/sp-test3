package com.db.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.CartMapper;
import com.db.pojo.Cart;

@Service(timeout = 5000)
public class DubboCartServiceImpl implements DubboCartService {

	@Autowired
	CartMapper cartMapper;

	@Override
	public List<Cart> findCartById(Long id) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
		queryWrapper.eq("user_id", id);
		List<Cart> list = cartMapper.selectList(queryWrapper);
		return list;
	}

	@Override
	public void deleteCartById(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>(cart);
		cartMapper.delete(queryWrapper);
	}

	@Override
	public void addCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
		Cart one = cartMapper.selectOne(queryWrapper);
		if (one == null) {
			cart.setCreated(new Date()).setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		} else {
			int num = one.getNum() + cart.getNum();
			Cart temp = new Cart();
			temp.setId(one.getId()).setNum(num).setUpdated(new Date());
			cartMapper.updateById(temp);
		}
	}

	@Override
	public void updateCartNum(Cart cart) {
		Cart temp = new Cart();
		temp.setNum(cart.getNum()).setUpdated(new Date());
		QueryWrapper<Cart> updateWrapper = new QueryWrapper<>();
		updateWrapper.eq("user_id", cart.getUserId()).eq("item_id", cart.getItemId());
		cartMapper.update(temp, updateWrapper);
	}

	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		List<Cart> selectList = cartMapper.selectList(queryWrapper);
		return selectList;
	}

	@Override
	public void deleteCartByItemsId(List<String> itemIds, Long userId) {
		for (String itemId : itemIds) {
			QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("user_id", userId).eq("item_id", itemId);
			cartMapper.delete(queryWrapper);
		}
	}

	@Override
	public int deleteByIds(Long[] ids) {
		List<Long> idList = Arrays.asList(ids);
		int rows = cartMapper.deleteBatchIds(idList);
		return rows;
	}
}
