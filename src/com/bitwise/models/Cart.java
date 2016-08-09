package com.bitwise.models;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.database.Products;
import com.bitwise.exceptions.CartIsFullException;

public class Cart {
	private List<Product> cartItems = new ArrayList<Product>();
//	private final List<Product> products = new Products().getList();
	
	public void addItem (Integer pid) {
		Product product = getProductFromListByProductID(pid);		 
		cartItems.add(product);
	}

	private boolean isOutOfStock(int stock) {
		return (stock - 1) < 0;
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
	
	public double calculateTotalPrice () {
		double total = 0.00d;
		for (Product prod: this.cartItems) {
			total += prod.getProdPrice();
		}
		return total;
	}
}
