package com.bitwise.database;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.models.Product;

public class Products {
	public static List<Product> getProducts() {
		return products;
	}

	public static void setProducts(List<Product> products) {
		Products.products = products;
	}

	public static List<Product> products = new ArrayList<Product>();
	
	public Products () {
//		this.initProducts();
	}
	
//	public void initProducts () {
//		products.add(new Product(1, "Army Knife", 1123.00d, "Roxane", 10));
//		products.add(new Product(2, "Lifeboy", 28.00d, "Soapa", 10));
//		products.add(new Product(3, "Sunsilk", 112.00d, "Shampo", 10));
//		products.add(new Product(4, "Wheel", 11.00d, "Clothia", 10));
//		products.add(new Product(5, "Surf Excel", 56.00d, "Clothia", 10));
//		products.add(new Product(6, "Vaseline White Beauty", 109.00d, "Roxane", 10));
//		products.add(new Product(7, "Sunglasses", 1129.00d, "Rayban", 10));
//		products.add(new Product(8, "Detol Handwash", 99.00d, "Soapa", 10));
//		products.add(new Product(9, "Washing Machine", 12000.00d, "Whirlpool", 10));
//		products.add(new Product(10, "Cook Book", 599.00d, "Antoine", 10));
//	}
	
	public List<Product> getList () {
		return this.products;
	}
	
	public Product getProductByProductID (Integer pid, List<Product> products) {
		Product product = null;
		for (Product prod: products) {
			if (prod.getPID() == pid) {
				product = prod;
			}
		}
		return product;
	}
}
