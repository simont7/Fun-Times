package com.ford.henrys.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ford.henrys.DiscountCriteria;
import com.ford.henrys.DiscountRule;
import com.ford.henrys.Product;
import com.ford.henrys.Unit;


@Service("DiscountRulesLoader")
public class DiscountRulesLoaderImpl implements DiscountRulesLoader {
	
	private static final Product SOUP = new Product("Soup", Unit.TIN);
	private static final Product BREAD = new Product("Bread", Unit.LOAF);
	private static final Product MILK = new Product("Milk", Unit.BOTTLE);
	private static final Product APPLES = new Product("Apples", Unit.SINGLE);
	
	private static final DiscountCriteria SOUP_OFFER_CRITERIA = 
			new DiscountCriteria(
					SOUP, 
					2, 
					LocalDate.now().minusDays(1),
					LocalDate.now().plusDays(7));
	private static final DiscountCriteria APPLES_CRITERIA = 
			new DiscountCriteria(
					APPLES,
					1,
					LocalDate.now().plusDays(3),
					LocalDate.now().plusMonths(2));
	
	private static final DiscountRule SOUP_OFFER = 
			new DiscountRule(SOUP_OFFER_CRITERIA, BREAD, new BigDecimal(0.5), 1);
	private static final DiscountRule APPLES_OFFER =
			new DiscountRule(APPLES_CRITERIA, APPLES, new BigDecimal(0.1), null);

	@Override
	public HashMap<String, DiscountRule> load() {
		HashMap<String, DiscountRule> rules = new HashMap<>();
		
		rules.put(SOUP.getName(), SOUP_OFFER);
		rules.put(APPLES.getName(), APPLES_OFFER);
		
		return rules;
	}

}
