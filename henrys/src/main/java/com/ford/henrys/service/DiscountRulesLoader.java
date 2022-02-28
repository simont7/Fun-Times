package com.ford.henrys.service;

import java.util.HashMap;

import com.ford.henrys.DiscountRule;

public interface DiscountRulesLoader {
	
	public HashMap<String, DiscountRule> load();

}
