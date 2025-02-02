package com.ford.henrys.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.henrys.BasketItem;
import com.ford.henrys.Discount;
import com.ford.henrys.DiscountRule;

@Service("discounter")
public class DiscounterImpl implements Discounter {

	private DiscountRulesLoader rulesLoader;
	
	private HashMap<String, DiscountRule> rules;
	
	@Autowired
	public DiscounterImpl(DiscountRulesLoader rulresLoader) {
		this.rulesLoader = rulresLoader;
	}
		
	private HashMap<String, DiscountRule> getRules() {
		if (null == rules) {
			rules = rulesLoader.load();
		}
		return rules;
	}

	@Override
	public Discount discount(BasketItem item) {
		
		DiscountRule rule = getRules().get(item.getStockItem().getProduct().getName());

		if (null != rule && rule.applyDiscount(item)) {
			
			// Apply multiple discounts if needed
			int maxItems = item.getQuantity()/rule.getCriteria().getMinimumQuantity();
			rule.getTargetDiscount().setMaxItems(maxItems);
			return rule.getTargetDiscount();
		}
		return null;
	}
}
