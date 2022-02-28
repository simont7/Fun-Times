package com.ford.henrys.service.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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
import com.ford.henrys.TillReceiptItem;
import com.ford.henrys.service.Discounter;
import com.ford.henrys.service.InitialProducts;
import com.ford.henrys.service.PriceCalculator;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {PriceCalculatorUnitTestAppConfig.class})
public class PriceCalculatorTest implements InitialProducts {
	
	@Autowired
	@Qualifier("mockDiscounter")
	private Discounter discounter;
	
	@Autowired
	private PriceCalculator priceCalculator;

	@Test
	public void test2SoupsAnd1Loaf() {

		assertNotNull("Discounter", discounter);
		
		Basket basket = new Basket();	

		when(discounter.discount(Mockito.any())).thenReturn(BREAD_DISCOUNT);

		basket.addItem(SOUP_ITEM, 2);
		basket.addItem(BREAD_ITEM, 1);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertNotNull("TillReciptItems null", items);
		assertEquals("Two items", 2, items.size());
		
		BigDecimal totalPrice = items.get(0).getToPay().add(items.get(1).getToPay());
		assertEquals("Total price ", formatValue(BigDecimal.valueOf(1.70)), totalPrice);
		
	}
	
	@Test
	public void test2SoupsAnd3Loaf() {

		assertNotNull("Discounter", discounter);
		
		Basket basket = new Basket();	

		when(discounter.discount(Mockito.any())).thenReturn(BREAD_DISCOUNT);

		basket.addItem(SOUP_ITEM, 2);
		basket.addItem(BREAD_ITEM, 3);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertNotNull("TillReciptItems null", items);
		assertEquals("Two items", 2, items.size());
		
		BigDecimal totalPrice = items.get(0).getToPay().add(items.get(1).getToPay());
		assertEquals("Total price ", formatValue(BigDecimal.valueOf(3.30)), totalPrice);
		
	}
	@Test
	public void test2ApplesBoughtIn5Days() {
		assertNotNull("Discounter", discounter);
		
		Basket basket = new Basket();	

		when(discounter.discount(Mockito.any())).thenReturn(APPLES_DISCOUNT);

		basket.addItem(APPLES_ITEM, 2, LocalDate.now().plusDays(5));
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertNotNull("TillReciptItems null", items);
		assertEquals("One items", 1, items.size());
		BigDecimal totalPrice = items.get(0).getToPay();
		assertEquals("Total price ", formatValue(BigDecimal.valueOf(0.18)), totalPrice);
	}
	
	@Test
	public void test2Apples() {
		assertNotNull("Discounter", discounter);
		
		Basket basket = new Basket();	

		basket.addItem(APPLES_ITEM, 2);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertNotNull("TillReciptItems null", items);
		assertEquals("One items", 1, items.size());
		BigDecimal totalPrice = items.get(0).getToPay();
		assertEquals("Total price ", formatValue(BigDecimal.valueOf(0.20)), totalPrice);
	}
	@Test
	public void test1Apple1Milk1Soup1Bread() {
		assertNotNull("Discounter", discounter);
		when(discounter.discount(Mockito.any())).thenReturn(null);
		Basket basket = new Basket();	

		basket.addItem(APPLES_ITEM, 1);
		basket.addItem(BREAD_ITEM, 1);
		basket.addItem(MILK_ITEM, 1);
		basket.addItem(SOUP_ITEM, 1);
		
		List<TillReceiptItem> items = priceCalculator.calculateCost(basket);
		
		assertNotNull("TillReciptItems null", items);
		assertEquals("4 items", 4, items.size());
		BigDecimal totalPrice = items.get(0).getToPay().add(
				                items.get(1).getToPay().add(
				                items.get(2).getToPay().add(
				                items.get(3).getToPay())));
		assertEquals("Total price ", formatValue(BigDecimal.valueOf(2.85)), totalPrice);
		
	}
	
	private BigDecimal formatValue(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}
}
