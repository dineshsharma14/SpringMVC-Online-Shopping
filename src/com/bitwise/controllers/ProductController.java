package com.bitwise.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.database.Products;
import com.bitwise.models.Cart;
import com.bitwise.models.Product;

@Controller
@RequestMapping ("/products")
public class ProductController {
	
	@RequestMapping (value = "/home", method = RequestMethod.GET)
	public String displayProducts (ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("title", "Products");
		
		return "home";
	}
	
	@RequestMapping (value = "/single", method = RequestMethod.GET)
	public ModelAndView singleProduct (ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("pid") Integer pid) {
		Product product = new Products().getProductByProductID(pid);

		model.addAttribute("prodName", product.getProdName());
		model.addAttribute("prodPrice", product.getProdPrice());
		model.addAttribute("prodSupplier", product.getSupplier());
		model.addAttribute("prodStock", product.getStock());
		model.addAttribute("prodID", product.getPID());
		return new ModelAndView("single", model);
	}
}
