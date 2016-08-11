package com.bitwise.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwise.database.Products;
import com.bitwise.database.Users;
import com.bitwise.helpers.UserValidator;
import com.bitwise.models.User;;

@Controller
public class LoginController {
//	@Autowired
//	private Products products;
	
	@RequestMapping (value = "/login", method = RequestMethod.GET)
	public String displayLogin (ModelMap model) {
		User user = new User ();
		model.addAttribute("title", "Login");
		model.addAttribute("user", user);
		model.addAttribute("login", "true");
		
		return ("index");
	}
	
	@RequestMapping (value = "/login", method = RequestMethod.POST)
	public String auth(ModelMap model, @ModelAttribute("user") User user, 
			BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String url = "index";
		url = handleUserInput(model, user, result, url, request, response, session);
		return (url);
	}

	private String handleUserInput(ModelMap model, User user, BindingResult result, String url, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		UserValidator validator = new UserValidator();
		validator.validate(user, result);
		url = authenticateUser(model, user, result, url, request, response, session);
		return url;
	}

	private String authenticateUser(ModelMap model, User user, BindingResult result, String url, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			url = "index";
		} else if (new Users().getUsers().contains(user)) {
			url = "redirect:/products/home";
			startSession(user, request, response, session);
		} else {
			model.addAttribute("error", "invalidUser");
		}
		return url;
	}

	private void startSession(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {			
		session.setAttribute("username", user.getUsername());
		session.setAttribute("sessID", session.getId());
		session.setMaxInactiveInterval(1000);
		Cookie cookie = new Cookie("sessID", session.getId());
		cookie.setMaxAge(10000);
		response.addCookie(cookie);
//		initStoreItems(request, session);
	}
	
	private void initStoreItems(HttpServletRequest req, HttpSession session) {
		/*if (req.getSession(false).getAttribute("products") == null
				&& products == null) {
			System.out.println("creating new product");
			products = new Products();
		}
		session.setAttribute("products", products);*/
	}
	
	
}
