package com.bitwise.controllers;

import java.net.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.database.Users;
import com.bitwise.helpers.UserValidator;
import com.bitwise.models.User;;

@Controller
public class LoginController {
		
	@RequestMapping (value = "/login", method = RequestMethod.GET)
	public String displayLogin (ModelMap model) {
		User user = new User ();
		model.addAttribute("title", "Login");
		model.addAttribute("user", user);
		
		return ("index");
	}
	
	@RequestMapping (value = "/login", method = RequestMethod.POST)
	public String auth(ModelMap model, @ModelAttribute("user") User user, 
			BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String url = "index";
		url = handleUserInput(model, user, result, url, request, response);
		return (url);
	}

	private String handleUserInput(ModelMap model, User user, BindingResult result, String url, HttpServletRequest request, HttpServletResponse response) {
		UserValidator validator = new UserValidator();
		validator.validate(user, result);
		url = authenticateUser(model, user, result, url, request, response);
		return url;
	}

	private String authenticateUser(ModelMap model, User user, BindingResult result, String url, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			url = "index";
		} else if (new Users().getUsers().contains(user)) {
			url = "redirect:/home";
			startSession(user, request, response);
		} else {
			model.addAttribute("error", "invalidUser");
		}
		return url;
	}

	private void startSession(User user, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("username", user.getUsername());
		session.setAttribute("sessID", session.getId());
	}
	
	
}
