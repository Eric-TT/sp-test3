package com.db.service;

import java.util.List;

import com.db.pojo.Order;
import com.db.pojo.OrderItem;

public interface DubboOrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);

	List<Order> findOrderByUserId(Long id);

	int deleteOrderById(String orderId);

	List<Order> findOrderByKey(String key, Long id);

	List<String> findItemIdsByOrderId(String orderId);

	List<OrderItem> commentOrderById(String orderId);
}
