package com.db.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.db.pojo.Item;
import com.db.service.DubboItemService;

@Controller
public class SearchController {

	@Reference(timeout = 5000, check = false)
	private DubboItemService itemService;

	@RequestMapping("/search")
	public String Search(String q, HttpServletRequest request, Model model) {
		q = request.getParameter("q");
		List<Item> list = itemService.findItemByName(q);

		model.addAttribute("itemList", list);

		return "search";
	}

	@RequestMapping("/products/{cid}")
	public String products(Model model, @PathVariable String cid) {
		List<Item> list = itemService.selectNameByCid(cid);
		model.addAttribute("itemList", list);

		return "products";
	}

	@RequestMapping("/thanks")
	public String toThaks() {

		return "thanks";
	}

}