package com.bitwise.helpers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bitwise.models.Order;
import com.bitwise.models.Product;

public class Utility {
	public static Product getItemFromGivenListByProductID(Integer pid, List<Product> products) {
		Product product = null;
		for (Product prod: products) {
			if (prod.getPID() == pid) {
				product = prod;
			}
		}
		return product;
	}
	
	public static Set<Order> removeDuplicateProducts(List<Product> cartItems) {
		Set<Order> orders = new HashSet<Order>();
		Order order;
		
		for (Product prod: cartItems) {
			
			int quantity = Collections.frequency(cartItems, prod);
			double price = prod.getProdPrice()*quantity;
			order = new Order (prod, quantity, price);
			orders.add(order);
		}
		return orders;
	}
}
