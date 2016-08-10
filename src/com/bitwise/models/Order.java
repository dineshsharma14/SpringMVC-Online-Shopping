package com.bitwise.models;

public class Order {
	private Product product;
	private Integer quantity;
	private Double price;
	
	public Order () {}
	
	public Order(Product prod, int quantity, double price) {
		this.product = prod;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Order other = (Order) obj;
		if (price == null) {
			if (other.price != null)return false;
		} else if (!price.equals(other.price))	return false;
		if (product == null) {
			if (other.product != null)	return false;
		} else if (!product.equals(other.product))	return false;
		if (quantity == null) {
			if (other.quantity != null)	return false;
		} else if (!quantity.equals(other.quantity))return false;
		return true;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
