package com.bitwise.helpers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bitwise.models.Cart;
import com.bitwise.models.Product;

public class PrintCartItems extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		JspWriter out = pageContext.getOut();
		String contextPath = req.getContextPath();
		StringBuilder sb = new StringBuilder(100);
		Cart cart = (Cart) req.getSession(false).getAttribute("cart");
		displayCartItems(out, contextPath, sb, cart);
	}

	private void displayCartItems(JspWriter out, String contextPath, StringBuilder sb, Cart cart) throws IOException {
		if (cart != null){
			List<Product> cartItems = cart.getCartItems();
			printCartItems(out, contextPath, sb, cartItems);
		} else {
			cartEmptyError(out);
		}
	}

	private void cartEmptyError(JspWriter out) throws IOException {
		out.println("<span class='red text'>Cart is empty</span>");
	}

	private void printCartItems(JspWriter out, String contextPath, StringBuilder sb, List<Product> cartItems)
			throws IOException {
		
		if (cartItems.isEmpty()) {
			sb.append("<span class='red-text red'>Cart is Empty</span> ");
			return;
		}
		for (Product prod : cartItems) {

			sb.append("<li>");
			sb.append("<div class='list-item-header'>");
			sb.append(prod.getProdName());
			sb.append("</div>");
			sb.append("<div class='list-item-body' >");
			sb.append("Price: " + prod.getProdPrice() + "<br/>");
			sb.append("Supplier: " + prod.getSupplier() + "<br/>");
			sb.append("Available Stock: " + prod.getStock() + "<br/>");
			sb.append("<a class='btn red' id='removeCartBtn' href='" + contextPath + "/cart/remove?pid=" + prod.getPID()
					+ "'>Remove</a>");
			sb.append("</div>");
			sb.append("</li>");

		}
		out.println("<ul class='list' >");
		out.println(sb.toString());
		out.println("</ul>");
	}

}
