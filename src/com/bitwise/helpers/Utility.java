package com.bitwise.helpers;

import java.util.List;

import com.bitwise.models.Product;

public class Utility {
	public static Product getProductFromTheGivenList (List<Product> list, Product prod) {
		for (Product p: list) {
			if (p == prod) {
				return p;
			}
		}
		return prod;
	}
}
