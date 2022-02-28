package com.ford.henrys.service.unit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ford.henrys.Basket;
import com.ford.henrys.BasketItem;
import com.ford.henrys.TillReceipt;
import com.ford.henrys.TillReceiptItem;
import com.ford.henrys.service.InitialProducts;
import com.ford.henrys.service.PriceCalculator;
import com.ford.henrys.service.Till;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TillUnitTestAppConfig.class})
public class TillTest implements InitialProducts {
	
	@Autowired
	@Qualifier("mockPriceCalculator")
	private PriceCalculator priceCalculator;
	
	@Autowired
	private Till tillService;

	@Test
	public void test2SoupsToday() {
		
		assertNotNull(tillService);
		
		// Inputs
		Basket basket = new Basket();
		basket.addItem(SOUP_ITEM, 2);

		// Expected outputs
		List<TillReceiptItem> items = new ArrayList<>();
		TillReceiptItem soups = new TillReceiptItem(
				SOUP, 2, new BigDecimal(1.30), new BigDecimal(0.00));
		items.add(soups);
		
		when(priceCalculator.calculateCost(Mockito.any())).thenReturn(items);
		
		TillReceipt receipt = tillService.checkout(basket);
		
		assertNotNull("Receipt returned", receipt);
		assertEquals("Gross price", new BigDecimal(1.30), receipt.getTotal());
		
	}
	@Test
	public void test2Soups1BreadToday() {
		
		assertNotNull(tillService);
		
		// Inputs
		Basket basket = new Basket();
		basket.addItem(SOUP_ITEM, 2);
		basket.addItem(BREAD_ITEM, 1);

		// Expected outputs
		List<TillReceiptItem> items = new ArrayList<>();
		TillReceiptItem soups = new TillReceiptItem(
				SOUP, 2, new BigDecimal(1.30), new BigDecimal(0.00));
		TillReceiptItem bread = new TillReceiptItem(
				BREAD, 1, new BigDecimal(0.80), new BigDecimal(0.40));
		items.add(soups);
		items.add(bread);
		
		when(priceCalculator.calculateCost(Mockito.any())).thenReturn(items);
		
		TillReceipt receipt = tillService.checkout(basket);
		
		assertNotNull("Receipt returned", receipt);
		assertEquals("Gross price", 
				formatValue(new BigDecimal(1.70)), 
				formatValue(receipt.getTotal()));		
	}
	
	private BigDecimal formatValue(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}
}
