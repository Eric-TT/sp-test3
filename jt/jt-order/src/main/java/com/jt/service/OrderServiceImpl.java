package com.jt.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;

public class OrderServiceImpl implements DubboOrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper itemMapper;
	@Autowired
	private OrderShippingMapper shippingMapper;
	@Override
	public String saveOrder(Order order) {
		String orderId=System.currentTimeMillis()+""+order.getUserId();
		//String orderId=UUID.randomUUID().toString().replace("-", "");
		return null;
	}

}
