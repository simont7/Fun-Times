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
	
	public void addRule(DiscountRule rule) {
		
		getRules().put(rule.getCriteria().getProduct().getName(), rule);
	}
	
	public HashMap<String, DiscountRule> getRules() {
		if (null == rules) {
			rules = rulesLoader.load();
		}
		return rules;
	}

	@Override
	public Discount discount(BasketItem item) {
		
		DiscountRule rule = getRules().get(item.getStockItem().getProduct().getName());

		if (null != rule && rule.applyDiscount(item)) {
			return rule.getTargetDiscount();
		}
		return null;
	}
}
