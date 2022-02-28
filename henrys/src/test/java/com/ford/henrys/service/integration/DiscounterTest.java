package com.ford.henrys.service.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ford.henrys.AppConfig;
import com.ford.henrys.BasketItem;
import com.ford.henrys.Discount;
import com.ford.henrys.service.Discounter;
import com.ford.henrys.service.InitialProducts;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class DiscounterTest implements InitialProducts {
		
	@Autowired
	private Discounter discounterService;
	
	@Test
	@Description("2 Soups bought today results in Bread discount")
	public void test2SoupsBoughtToday() {
		BasketItem item = new BasketItem(SOUP_ITEM, 2);
		
		Discount discount = discounterService.discount(item);
		assertTrue("Discount found", null != discount);	
		assertEquals("Bread discount", BREAD, discount.getProduct());
		assertEquals("Discount value", new BigDecimal(0.5), discount.getDiscount());
		assertEquals("Limited to one item", Integer.valueOf(1), discount.getMaxItems());
		
	}
	
	@Test
	@Description("2 Tins of soups bought in 8 days results in no discount")
	public void test2SoupsBoughtIn8Days() {
		BasketItem item = new BasketItem(SOUP_ITEM, 2, LocalDate.now().plusDays(8));
		
		Discount discount = discounterService.discount(item);
		assertTrue("Discount found", null == discount);	
	}

	@Test
	@Description("An apple bought today has no discount offer")
	public void testAnAppleBoughtToday() {
		BasketItem item = new BasketItem(APPLES_ITEM, 1);
		
		Discount discount = discounterService.discount(item);
		assertTrue("Discount not found", null == discount);	
	}
	
	@Test
	@Description("6 apples bought in 5 days time has a 10% off offer")
	public void testAnAppleBoughtIn5Days() {
		BasketItem item = new BasketItem(APPLES_ITEM, 1, LocalDate.now().plusDays(5));
		
		Discount discount = discounterService.discount(item);
		assertTrue("Discount found", null != discount);	
		assertEquals("Apples discount", APPLES, discount.getProduct());
		assertEquals("Discount value", new BigDecimal(0.1), discount.getDiscount());
		assertEquals("Not limited", null, discount.getMaxItems());
	}
	@Test
	@Description("3 soups bought today")
	public void test3SoupsBoughtToday() {
		
		BasketItem item = new BasketItem(SOUP_ITEM, 3);
		
		Discount discount = discounterService.discount(item);
		assertTrue("Discount found", null != discount);	
		assertEquals("Bread discount", BREAD, discount.getProduct());
		assertEquals("Discount value", new BigDecimal(0.5), discount.getDiscount());
		assertEquals("Limited to one item", Integer.valueOf(1), discount.getMaxItems());

	}
}
