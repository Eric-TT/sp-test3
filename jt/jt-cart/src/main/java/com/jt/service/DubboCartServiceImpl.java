package com.jt.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.CatMapper;
import com.jt.pojo.Cart;


@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService{
	@Autowired
	private CatMapper catMapper;

	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		return catMapper.selectList(queryWrapper);
	}

	@Override
	public void deleteCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>(cart);
		catMapper.delete(queryWrapper);
	}

	@Override
	public void saveCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", cart.getItemId())
					.eq("user_id", cart.getUserId());
		Cart cartDB = catMapper.selectOne(queryWrapper);
		if (cartDB==null) {
			cart.setCreated(new Date())
				.setUpdated(cart.getCreated());
			catMapper.insert(cart);
		}else {
			int num=cartDB.getNum()+cart.getNum();
			Cart cartTemp = new Cart();
			cartTemp.setId(cartDB.getId())
					.setNum(num)
					.setUpdated(new Date());
			catMapper.updateById(cartTemp);
		}
		
	}

	@Override
	public void updateCartNum(Cart cart) {
		Cart cartTemp=new Cart();
		cartTemp.setNum(cart.getNum())
				.setUpdated(new Date());
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("item_id", cart.getItemId())
					.eq("user_id", cart.getUserId());
		catMapper.update(cartTemp, updateWrapper);
		
	}
}
