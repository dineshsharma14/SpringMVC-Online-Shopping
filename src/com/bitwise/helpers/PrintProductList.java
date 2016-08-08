package com.bitwise.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bitwise.database.Products;
import com.bitwise.models.Product;

public class PrintProductList extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		PrintWriter out = res.getWriter();
		
		List<Product> products = new Products().getList();
		
		for (Product prod : products) {
			out.println("<div class='col m4'>");
			out.println("<div class='item-container'>");
			out.println("<div class='item-header' >");
			out.println(prod.getProdName());
			out.println("</div>");
			out.println("<div class='item-content' >");
			out.println("Supplier: " + prod.getSupplier() + "<br/>");
			out.println("Price: " + prod.getProdPrice() + "<br/>");
			out.println("Product ID: " + prod.getPID() + "<br/>");
			out.println("Available Stock: " + prod.getStock() + "<br/>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
		}
		
	}

}
