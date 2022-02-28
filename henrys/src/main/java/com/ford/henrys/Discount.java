package com.ford.henrys;

import java.math.BigDecimal;

public class Discount {
	
	private Product product;
	private BigDecimal discount;
	private Integer maxItems;
	
	public Discount(Product product, BigDecimal discount, Integer maxItems) {
		setProduct(product);
		setDiscount(discount);
		setMaxItems(maxItems);		
	}	

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getMaxItems() {
		return maxItems;
	}
	public void setMaxItems(Integer maxItems) {
		this.maxItems = maxItems;
	}
}
