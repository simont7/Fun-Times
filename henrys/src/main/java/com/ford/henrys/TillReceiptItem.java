package com.ford.henrys;

import java.math.BigDecimal;

public class TillReceiptItem {
	
	private Product product;
	private int quantity;
	private BigDecimal grossPrice;
	private BigDecimal discount;
	
	public TillReceiptItem () {}
	
	public TillReceiptItem(
			Product product, 
			int quantity, 
			BigDecimal grossPrice,
			BigDecimal discount) {
		this.product = product;
		this.quantity = quantity;
		this.grossPrice = grossPrice;
		this.discount = discount;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(BigDecimal grossPrice) {
		this.grossPrice = grossPrice;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getToPay() {
		return grossPrice.subtract(discount);
	}
}
