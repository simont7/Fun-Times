package com.ford.henrys.service;

import java.util.List;

import com.ford.henrys.Basket;
import com.ford.henrys.TillReceiptItem;

public interface PriceCalculator {
	
	public List<TillReceiptItem> calculateCost(Basket basket);

}
