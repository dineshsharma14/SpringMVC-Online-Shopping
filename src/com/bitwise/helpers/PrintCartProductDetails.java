package com.bitwise.helpers;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bitwise.models.Cart;
import com.bitwise.models.Order;
import com.bitwise.models.Product;

public class PrintCartProductDetails extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		JspWriter out = pageContext.getOut();
		String contextPath = req.getContextPath();
		StringBuilder sb = new StringBuilder(100);
		Cart cart = (Cart) req.getSession(false).getAttribute("cart");
		List<Product> cartItems = cart.getCartItems();
		Set<Order> orders = Utility.removeDuplicateProducts(cartItems);
		
		
		
		for (Order prod: orders) {
			sb.append("<tr>")
			.append("<td>").append(prod.getProduct().getPID()).append("</td>")
			.append("<td>").append(prod.getProduct().getProdName()).append("</td>")
			.append("<td>").append(prod.getQuantity()).append("</td>")
			.append("<td>").append(prod.getPrice()).append("</td>")
			.append("</tr>");
		}
		
		out.println(sb.toString());
	}

}
