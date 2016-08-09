package com.bitwise.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.models.Cart;

@Controller
@RequestMapping ("/cart")
public class CartController {
	
	@RequestMapping (value = "/add", method = RequestMethod.GET)
	public @ResponseBody String addItem (ModelMap model, 
			HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer pid) {
		int cartSize = addItemToCart(req, pid);
		String response = ""+cartSize;
		return response;
	}
	
	@RequestMapping (value = "/remove", method = RequestMethod.GET)
	public @ResponseBody String removeItem (ModelMap model, 
			HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer pid) {
		int cartSize = removeItemFromCartByProductID(req, pid);
		String response = ""+cartSize;
		return response;
	}
	
	@RequestMapping (value = "/size", method = RequestMethod.GET)
	public @ResponseBody String cartSize(HttpServletRequest req, HttpServletResponse res) {
		Cart cart = (Cart)req.getSession(false).getAttribute("cart");
		String cartSize = "" + cart.getCartSize();
		return cartSize;
	}

	private int removeItemFromCartByProductID(HttpServletRequest req, Integer pid) {
		if (req.getSession(false).getAttribute("cart") != null ) {
			Cart cart = (Cart) req.getSession(false).getAttribute("cart");
			cart.removeItem(pid);
			req.getSession(false).setAttribute("cart", cart);
			return cart.getCartSize();
		}
		return 0;
	}
	
	@RequestMapping (value = "/display", method = RequestMethod.GET)
	public ModelAndView displayCart (ModelMap model) {
		model.addAttribute("title", "Cart");
		return new ModelAndView("display", model);
	}

	private int addItemToCart(HttpServletRequest req, Integer pid) {
		if (req.getSession(false).getAttribute("cart") == null) {
			Cart cart = new Cart();
			cart.addItem(pid);
			req.getSession(false).setAttribute("cart", cart);
			return 1;
		} else {
			Cart cart = (Cart) req.getSession(false).getAttribute("cart");
			cart.addItem(pid);
			req.getSession(false).setAttribute("cart", cart);
			return cart.getCartSize();
		}
	}
}
