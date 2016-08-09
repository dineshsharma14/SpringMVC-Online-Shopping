package com.bitwise.models;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.database.Products;

public class Cart {
	private List<Product> cartItems = new ArrayList<Product>();
	private final List<Product> products = new Products().getList();
	
	public void addItem (Integer pid) {
		Product product = getProductFromListByProductID(pid);
		cartItems.add(product);
	}
	
	public void removeItem (Integer pid) {
		Product product = getProductFromListByProductID(pid);
		cartItems.remove(product);
	}
	
	public int getCartSize() {
		return this.cartItems.size();
	}

	public List<Product> getCartItems() {
		return cartItems;
	}

	public Product getProductFromListByProductID(Integer pid) {
		Product product = null;
		for (Product prod: products) {
			if (prod.getPID() == pid) {
				product = prod;
			}
		}
		return product;
	}
}
