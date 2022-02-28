package com.ford.henrys.service;

import com.ford.henrys.BasketItem;
import com.ford.henrys.Discount;
import com.ford.henrys.DiscountRule;

public interface Discounter {
	Discount discount(BasketItem item);
	void addRule(DiscountRule discountRule);

}
