package com.ford.henrys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.henrys.Basket;
import com.ford.henrys.TillReceipt;


@Service("till")
public class TillImpl implements Till {
	
	private PriceCalculator priceCalculator;
	
	@Autowired
	public TillImpl(PriceCalculator priceCalculator) {
		this.priceCalculator = priceCalculator;		
	}

	@Override
	public TillReceipt checkout(Basket basket) {
		
		TillReceipt receipt = new TillReceipt();
			
		receipt.setEntry(priceCalculator.calculateCost(basket));
		return receipt;
	}

}
