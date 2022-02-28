package com.ford.henrys;

import java.time.LocalDate;

public class DiscountCriteria {
	
	private Product product;
	private int minimumQuantity;
	private LocalDate validFrom;
	private LocalDate validTo;
	
	public DiscountCriteria() {
		
	}
	
	public DiscountCriteria(
			Product product, 
			int minimumQuantity, 
			LocalDate validFrom, 
			LocalDate validTo) {
		
		setProduct(product);
		setMinimumQuantity(minimumQuantity);
		setValidFrom(validFrom);
		setValidTo(validTo);
		
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getMinimumQuantity() {
		return minimumQuantity;
	}
	public void setMinimumQuantity(int minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
	public LocalDate getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDate getValidTo() {
		return validTo;
	}
	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
}
