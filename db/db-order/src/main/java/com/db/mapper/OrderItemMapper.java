package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.pojo.OrderItem;

public interface OrderItemMapper extends BaseMapper<OrderItem> {

	String[] findOrderItemByKey(String key);

}