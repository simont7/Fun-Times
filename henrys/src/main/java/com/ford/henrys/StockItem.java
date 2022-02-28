package com.ford.henrys;

import java.math.BigDecimal;

public class StockItem {
	
	private Product product;
	private BigDecimal cost;
	
	public StockItem(Product product, BigDecimal cost) {
		setProduct(product);
		setCost(cost);
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
