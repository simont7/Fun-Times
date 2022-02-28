package com.ford.henrys.service.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ford.henrys.AppConfig;
import com.ford.henrys.Basket;
import com.ford.henrys.TillReceipt;
import com.ford.henrys.TillReceiptItem;
import com.ford.henrys.service.InitialProducts;
import com.ford.henrys.service.Till;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class TillTest implements InitialProducts {
	
	private static final Logger LOGGER = Logger.getLogger("TillTest");

	@Autowired
	private Till tillService;

	@Test
	@Description("Price a basket containing: 3 tins of soup and 2 loaves of bread, bought today")
	public void test3tinsSoup2LoavesBoughtToday() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(SOUP_ITEM, 3);
		basket.addItem(BREAD_ITEM, 2);
		assertTrue("Two products in the basket", basket.getContents().size() == 2);
		
		TillReceipt receipt = tillService.checkout(basket);

		for (TillReceiptItem item : receipt.getEntries()) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}			

		assertEquals("Expected total price", 
				formatValue(BigDecimal.valueOf(3.15)),
				receipt.getTotal());
	}
	
	@Test
	@Description("Price a basket containing: 6 apples and a bottle of milk, bought today")
	public void test6ApplesAndABottleOfMilkBoughtToday() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(APPLES_ITEM, 6);
		basket.addItem(MILK_ITEM, 1);
		assertTrue("Two products in the basket", basket.getContents().size() == 2);		
		
		TillReceipt receipt = tillService.checkout(basket);

		for (TillReceiptItem item : receipt.getEntries()) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}	
		
		assertEquals("Expected total price ", 
				formatValue(BigDecimal.valueOf(1.90)),
				receipt.getTotal());
	}
	
	@Test
	@Description("Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time")
	public void test6ApplesAndABottleOfMilkBoughtIn5Days () {
		Basket basket = new Basket();

		assertTrue("Basket is empty", basket.getContents().size() == 0);
		
		basket.addItem(APPLES_ITEM, 6, LocalDate.now().plusDays(5));
		basket.addItem(MILK_ITEM, 1, LocalDate.now().plusDays(5));
		
		assertTrue("Two products in the basket", basket.getContents().size() == 2);		
			
		TillReceipt receipt = tillService.checkout(basket);

		for (TillReceiptItem item : receipt.getEntries()) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}			
		
		assertEquals("Expected total price", 
				formatValue(BigDecimal.valueOf(1.84)),
				formatValue(receipt.getTotal()));
	}
	
	@Test
	@Description("Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time")
	public void test3Apples2TinsOfSoupAndALoafOfBreadBought5Days () {
		Basket basket = new Basket();

		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(APPLES_ITEM, 3, LocalDate.now().plusDays(5));
		basket.addItem(SOUP_ITEM, 2, LocalDate.now().plusDays(5));
		basket.addItem(BREAD_ITEM, 1, LocalDate.now().plusDays(5));
		
		assertTrue("Two products in the basket", basket.getContents().size() == 3);			
		
		TillReceipt receipt = tillService.checkout(basket);
		
		for (TillReceiptItem item : receipt.getEntries()) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}		
		assertEquals("Expected total price ", 
				formatValue(BigDecimal.valueOf(1.97)),
				formatValue(receipt.getTotal()));
		
	}
	private BigDecimal formatValue(BigDecimal value, int scale) {
		return value.setScale(scale, RoundingMode.HALF_UP);
	}
	private BigDecimal formatValue(BigDecimal value) {
		return formatValue(value, 2);
	}
}
