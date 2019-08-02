package com.db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.OrderItemMapper;
import com.db.mapper.OrderMapper;
import com.db.mapper.OrderShippingMapper;
import com.db.pojo.Order;
import com.db.pojo.OrderItem;
import com.db.pojo.OrderShipping;

@Service
public class OrderServiceImpl implements DubboOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	@Override
	@Transactional // 进行事务控制
	public String saveOrder(Order order) {
		String orderId = System.currentTimeMillis() + "" + order.getUserId();
		Date now = new Date();
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setCreated(now);
		order.setUpdated(now);
		orderMapper.insert(order);

		OrderShipping shipping = order.getOrderShipping();
		shipping.setOrderId(orderId);
		shipping.setCreated(now);
		shipping.setUpdated(now);
		orderShippingMapper.insert(shipping);

		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			orderItem.setCreated(now);
			orderItem.setUpdated(now);
			orderItemMapper.insert(orderItem);
		}
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		Order order = orderMapper.selectById(id);
		OrderShipping shipping = orderShippingMapper.selectById(id);
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", id);
		List<OrderItem> items = orderItemMapper.selectList(queryWrapper);
		order.setOrderItems(items).setOrderShipping(shipping);
		return order;
	}

	@Override
	public List<Order> findOrderByUserId(Long id) {
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", id);
		List<Order> orders = orderMapper.selectList(queryWrapper);
		setOrders(orders);
		return orders;
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public int deleteOrderById(String orderId) {
		int rows = orderMapper.deleteById(orderId);
		orderShippingMapper.deleteById(orderId);
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId);
		orderItemMapper.delete(queryWrapper);
		return rows;
	}

	@Override
	public List<Order> findOrderByKey(String key, Long userId) {
		List<Order> orders = new ArrayList<Order>();
		key = key.replaceAll(" ", "");
		boolean flag = "商品名称、商品编号、订单编号".equals(key) || StringUtils.isEmpty(key);
		if (!flag) {
			String[] orderIds = orderItemMapper.findOrderItemByKey(key);
			for (String orderId : orderIds) {
				QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("order_id", orderId).eq("user_id", userId);
				Order order = orderMapper.selectOne(queryWrapper);
				if (order != null) {
					orders.add(order);
				}
			}
		} else {
			QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("user_id", userId);
			orders = orderMapper.selectList(queryWrapper);
		}
		setOrders(orders);
		return orders;
	}

	private void setOrders(List<Order> orders) {
		for (Order order : orders) {
			String orderId = order.getOrderId();
			OrderShipping shipping = orderShippingMapper.selectById(orderId);
			QueryWrapper<OrderItem> query = new QueryWrapper<OrderItem>();
			query.eq("order_id", orderId);
			List<OrderItem> orderItems = orderItemMapper.selectList(query);
			order.setOrderShipping(shipping).setOrderItems(orderItems);
		}
	}

	@Override
	public List<String> findItemIdsByOrderId(String orderId) {
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId);
		List<OrderItem> items = orderItemMapper.selectList(queryWrapper);
		List<String> itemIds = new ArrayList<>();
		for (OrderItem item : items) {
			String itemId = item.getItemId();
			if (!StringUtils.isEmpty(itemId)) {
				itemIds.add(itemId);
			}
		}
		return itemIds;
	}

	@Override
	public List<OrderItem> commentOrderById(String orderId) {
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId);
		List<OrderItem> itemList = orderItemMapper.selectList(queryWrapper);
		//OrderItem orderItem = orderItemMapper.selectOne(queryWrapper);
		return itemList;
	}
}
