package com.bitwise.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/products")
public class ProductController {
	
	@RequestMapping (value = "/home", method = RequestMethod.GET)
	public String displayProducts (ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("title", "Products");
		
		return "home";
	}
}
