package com.bitwise.models;

import java.util.ArrayList;
import java.util.List;
import com.bitwise.models.Product;

import com.bitwise.database.Products;

public class Cart {
	private List<Product> cartItems = new ArrayList<Product>();
//	private final List<Product> products = new Products().getList();
	
	public void addItem (Product product) { 
		cartItems.add(product);
	}

	private boolean isOutOfStock(int stock) {
		return (stock - 1) < 0;
	}
	
	public void removeItem (Product product) {
		cartItems.remove(product);
	}
	
	public int getCartSize() {
		return this.cartItems.size();
	}

	public List<Product> getCartItems() {
		return cartItems;
	}
	
	public double calculateTotalPrice () {
		double total = 0.00d;
		for (Product prod: this.cartItems) {
			total += prod.getProdPrice();
		}
		return total;
	}
}
