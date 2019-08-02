package com.jt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Reference(timeout = 3000,check = false)
	private DubboCartService cartService;
	@Reference(timeout = 3000,check = false)
	private DubboOrderService orderService;
	@RequestMapping("/create")
	public String create(Model model) {
		Long userId=UserThreadLocal.get().getId();
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
	}
	
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		Long userId=UserThreadLocal.get().getId();
		order.setUserId(userId);
		String orderId=orderService.saveOrder(order);
		if (StringUtils.isEmpty(orderId)) {
			return SysResult.fail();
		}
		return SysResult.success(orderId);
	}

}
