package com.bitwise.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bitwise.database.Products;
import com.bitwise.exceptions.SessionExpiredException;
import com.bitwise.models.Product;

public class PrintProductList extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		JspWriter out = pageContext.getOut();
		String contextPath = req.getContextPath();
		List<Product> products = ((Products)req.getSession(false).getAttribute("products")).getList();
//		List<Product> products = new Products().getList();
		if (products == null || products.isEmpty()) {
			throw new SessionExpiredException();
		}
		printProductList(out, contextPath, products);
		
	}

	private void printProductList(JspWriter out, String contextPath, List<Product> products) throws IOException {
		for (Product prod : products) {
			out.println("<div class='col m4'>");
			out.println("<div class='item-container'>");
			out.println("<div class='item-header' >");
			out.println("<a href='"+contextPath+"/products/single?pid="
					+ prod.getPID() + "'> ");
			out.println(prod.getProdName());
			out.println("</a>");
			out.println("</div>");
			out.println("<div class='item-content' >");
			out.println("Supplier: " + prod.getSupplier() + "<br/>");
			out.println("Price: " + prod.getProdPrice() + "<br/>");
			out.println("Product ID: " + prod.getPID() + "<br/>");
			out.println("Available Stock: " + prod.getStock() + "<br/>");
			out.println("<a id='addCartBtn' class='btn green' href='"+contextPath+"/cart/add?pid="+prod.getPID()+"' >Add to Cart</a>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
		}
	}

}
