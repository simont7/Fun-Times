package com.ford.henrys.service;

import com.ford.henrys.BasketItem;
import com.ford.henrys.Discount;

public interface Discounter {
	Discount discount(BasketItem item);

}
