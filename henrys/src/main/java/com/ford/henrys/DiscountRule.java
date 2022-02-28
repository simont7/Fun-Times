package com.ford.henrys;

import java.math.BigDecimal;

public class DiscountRule {
	
	private DiscountCriteria criteria;	
	private Discount targetDiscount;
	
	public DiscountRule(
			DiscountCriteria criteria, 
			Product targetProduct,
			BigDecimal discount,
			Integer targetQuantity) {
		this.criteria = criteria;
		Discount test = new Discount(targetProduct, discount, targetQuantity);
		this.targetDiscount = new Discount(targetProduct, discount, targetQuantity);
	}
	
	public DiscountCriteria getCriteria() {
		return criteria;
	}

	public Discount getTargetDiscount() {
		return targetDiscount;
	}
	public boolean applyDiscount(BasketItem item) {
		
		if (item.getStockItem().getProduct().getName().equals(criteria.getProduct().getName()) &&
			item.getQuantity() >= criteria.getMinimumQuantity() &&
			item.getDateAdded().compareTo(criteria.getValidFrom()) >= 0 &&
			item.getDateAdded().compareTo(criteria.getValidTo()) <= 0) {
			
			return true;
		}
		else {
			return false;
		}
	}
}
