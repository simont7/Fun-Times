package com.ford.henrys.service.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ford.henrys.AppConfig;
import com.ford.henrys.Basket;
import com.ford.henrys.TillReceiptItem;
import com.ford.henrys.service.InitialProducts;
import com.ford.henrys.service.PriceCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class PriceCalculatorTest implements InitialProducts{
	
	@Autowired
	private PriceCalculator priceCalculator;
	
	private static final Logger LOGGER = Logger.getLogger("PriceCalculatorTest");

	@Test
	public void testTwoTinsOfSoupPrice() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(SOUP_ITEM, 2);
		assertTrue("One product type in the basket", basket.getContents().size() == 1);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertEquals("One receipt item", items.size(), 1);
		assertEquals("Total price for soup",
				formatValue(BigDecimal.valueOf(1.30)),
				items.get(0).getGrossPrice());

		for (TillReceiptItem item : items) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}
	}
	@Test
	public void testTwoTinsOfSoupAndALoaf() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(SOUP_ITEM, 2);
		basket.addItem(BREAD_ITEM, 1);
		assertTrue("Two products in the basket", basket.getContents().size() == 2);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertEquals("Two receipt items", items.size(), 2);
		assertEquals("Total price for 2 soup and a loaf",
				formatValue(BigDecimal.valueOf(2.10)),
				items.get(0).getGrossPrice().add(items.get(1).getGrossPrice()));
		
		BigDecimal discountTotal = items.get(0).getDiscount().add(items.get(1).getDiscount());
		assertEquals("Discount for loaf",
				formatValue(BigDecimal.valueOf(0.40), 2),
				discountTotal);
		
		for (TillReceiptItem item : items) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}
	}
	@Test
	@Description("Price 6 apples and a bottle of milk, bought today")
	public void test6ApplesAndABottleOfMilkBoughtToday() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(APPLES_ITEM, 6);
		basket.addItem(MILK_ITEM, 1);
		assertTrue("Two product types in the basket", basket.getContents().size() == 2);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertEquals("Two receipt items", items.size(), 2);
		
		// No discount
		assertEquals("Total price for 6 apples and a bottle of milk",
				formatValue(BigDecimal.valueOf(1.90)),
				items.get(0).getGrossPrice().add(items.get(1).getGrossPrice()));
		
		BigDecimal discountTotal = items.get(0).getDiscount().add(items.get(1).getDiscount());
		assertEquals("Discount for loaf",
				formatValue(BigDecimal.valueOf(0.00)),
				discountTotal);
		
		for (TillReceiptItem item : items) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice());
		}
	}
	
	@Test
	@Description("Price 6 apples and a bottle of milk, bought in 5 days")
	public void test6ApplesAndABottleOfMilkBoughtIn5Days() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(APPLES_ITEM, 6, LocalDate.now().plusDays(5));
		basket.addItem(MILK_ITEM, 1, LocalDate.now().plusDays(5));
		assertTrue("Two product types in the basket", basket.getContents().size() == 2);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertEquals("Two receipt items", items.size(), 2);

		// Discount of 10% for Apples
		assertEquals("Total price for 6 apples and a bottle of milk",
				formatValue(BigDecimal.valueOf(1.90)),
				formatValue(items.get(0).getGrossPrice().add(items.get(1).getGrossPrice())));
		
		BigDecimal discountTotal = items.get(0).getDiscount().add(items.get(1).getDiscount());
		assertEquals("Discount for apples",
				formatValue(BigDecimal.valueOf(0.06)),
				formatValue(discountTotal));
		
		BigDecimal priceToPay = items.get(0).getToPay().add(items.get(1).getToPay());
		assertEquals("Price to pay", 
				formatValue(BigDecimal.valueOf(1.84)),
				formatValue(priceToPay));		
		
		for (TillReceiptItem item : items) {
			LOGGER.info("Product " + item.getProduct().getName() + " : " +
				"Quantity - " + item.getQuantity() + " : " +
				"Discount - " + item.getDiscount() + " : " +
				"Gross Price - " + item.getGrossPrice() + " : " +
				"Price to pay - " + item.getToPay());
		}
	}

	@Test
	@Description("Price 3 soups and two loaves of bread bought today")
	public void test3SoupsAnd2LoavesBoughtToday() {
		Basket basket = new Basket();
		
		assertTrue("Basket is empty", basket.getContents().size() == 0);
		basket.addItem(SOUP_ITEM, 3);
		basket.addItem(BREAD_ITEM, 2);
		assertTrue("Two product types in the basket", basket.getContents().size() == 2);

		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertEquals("Two receipt items", items.size(), 2);
		assertEquals("Total discount", 
				formatValue(BigDecimal.valueOf(0.4),2),
				items.get(0).getDiscount().add(items.get(1).getDiscount()));

	}
	private BigDecimal formatValue(BigDecimal value, int scale) {
		return value.setScale(scale, RoundingMode.HALF_UP);
	}
	private BigDecimal formatValue(BigDecimal value) {
		return formatValue(value, 2);
	}
}
