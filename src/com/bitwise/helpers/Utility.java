package com.bitwise.helpers;

import java.util.List;

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
}
