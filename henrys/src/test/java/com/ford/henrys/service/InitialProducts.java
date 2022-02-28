package com.ford.henrys.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ford.henrys.Discount;
import com.ford.henrys.DiscountCriteria;
import com.ford.henrys.DiscountRule;
import com.ford.henrys.Product;
import com.ford.henrys.StockItem;
import com.ford.henrys.Unit;

public interface InitialProducts {
	
	Product SOUP = new Product("Soup", Unit.TIN);
	Product BREAD = new Product("Bread", Unit.LOAF);
	Product MILK = new Product("Milk", Unit.BOTTLE);
	Product APPLES = new Product("Apples", Unit.SINGLE);
	
	StockItem SOUP_ITEM = new StockItem(SOUP, new BigDecimal(0.65));
	StockItem BREAD_ITEM = new StockItem(BREAD, new BigDecimal(0.8));
	StockItem APPLES_ITEM = new StockItem(APPLES, new BigDecimal(0.1));
	StockItem MILK_ITEM = new StockItem(MILK, new BigDecimal(1.3));
	
	Discount BREAD_DISCOUNT = new Discount(BREAD, new BigDecimal(0.5), 1);
	Discount APPLES_DISCOUNT = new Discount(APPLES, new BigDecimal(0.1), null);

	DiscountCriteria SOUP_OFFER_CRITERIA = 
			new DiscountCriteria(
					SOUP, 
					2, 
					LocalDate.now().minusDays(1),
					LocalDate.now().plusDays(7));
	DiscountCriteria APPLES_CRITERIA = 
			new DiscountCriteria(
					APPLES,
					1,
					LocalDate.now().plusDays(3),
					LocalDate.now().plusMonths(2));
	
	DiscountRule SOUP_OFFER = 
			new DiscountRule(SOUP_OFFER_CRITERIA, BREAD, new BigDecimal(0.5), 1);
	DiscountRule APPLES_OFFER =
			new DiscountRule(APPLES_CRITERIA, APPLES, new BigDecimal(0.1), null);
}
