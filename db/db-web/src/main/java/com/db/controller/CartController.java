package com.db.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.db.pojo.Cart;
import com.db.pojo.User;
import com.db.service.DubboCartService;
import com.db.util.UserThreadLocal;
import com.db.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Reference(timeout = 5000, check = true)
	private DubboCartService cartService;

	@RequestMapping("/show")
	public String cartPage(Model model, HttpServletRequest request) {
		User user = getUser(request);
		Long id = user.getId();
		List<Cart> cartList = cartService.findCartById(id);
		model.addAttribute(cartList);
		return "cart";
	}

	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(Cart cart, HttpServletRequest request) {
		User user = getUser(request);
		cart.setUserId(user.getId());
		cartService.updateCartNum(cart);
		return SysResult.success();
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId, HttpServletRequest request) {
		User user = getUser(request);
		Cart cart = new Cart();
		cart.setItemId(itemId).setUserId(user.getId());
		cartService.deleteCartById(cart);
		return "redirect:/cart/show";
	}

	@RequestMapping("/add/{itemId}")
	public String addCart(Cart cart, HttpServletRequest request) {
		System.out.println(cart);
		User user = getUser(request);
		cart.setUserId(user.getId());
		System.out.println(user);
		cartService.addCart(cart);
		return "redirect:/cart/show";
	}

	private User getUser(HttpServletRequest request) {
		User user = UserThreadLocal.get();
		return user;
	}
	
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public SysResult deleteByIds(Long... ids) {
		System.out.println(Arrays.toString(ids));
		int rows = cartService.deleteByIds(ids);
		if (rows<1) {
			return SysResult.fail();
		}else {
			return SysResult.success();
		}
	}

}
