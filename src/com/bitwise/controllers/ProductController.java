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
import com.bitwise.models.Product;

@Controller
@RequestMapping ("/products")
public class ProductController {
	
	@RequestMapping (value = "/home", method = RequestMethod.GET)
	public ModelAndView displayProducts (ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("title", "Products");
		StringBuilder sb = productsList(request);
		model.addAttribute("productsList", sb.toString());
		return new ModelAndView("home", model);
	}

	private StringBuilder productsList(HttpServletRequest request) {
		List<Product> products = ((Products) request.getSession(false).getAttribute("products")).getList();
		StringBuilder sb = new StringBuilder(100);
		String contextPath = request.getContextPath();
		for (Product prod: products) {
			sb.append("<div class='col m4'>")
			.append("<div class='item-container'>")
			.append("<div class='item-header' >")
			.append("<a href='"+contextPath+"/products/single?pid="
					+ prod.getPID() + "'> ")
			.append(prod.getProdName())
			.append("</a>")
			.append("</div>")
			.append("<div class='item-content' >")
			.append("Supplier: " + prod.getSupplier() + "<br/>")
			.append("Price: " + prod.getProdPrice() + "<br/>")
			.append("Product ID: " + prod.getPID() + "<br/>")
			.append("Available Stock: " + prod.getStock() + "<br/>")
			.append("<a id='addCartBtn' class='btn green' href='"+contextPath+"/cart/add?pid="+prod.getPID()+"' >Add to Cart</a>")
			.append("</div>")
			.append("</div>")
			.append("</div>");
		}
		return sb;
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
