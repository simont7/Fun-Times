package com.ford.henrys;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ford.henrys.service.InitialProducts;

public class BasketTest implements InitialProducts {
	
	
	private Basket testSubject = new Basket();

	
	@Test
	public void testBasketEmpty() {
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
	}
	
	@Test
	public void testAddAnItem() {
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
		testSubject.addItem(SOUP_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 1);
		assertTrue("Item is soup", testSubject.getContents().get(SOUP.getName()) != null);
		assertTrue("Just one tin", testSubject.getContents().get(SOUP.getName()).getQuantity() == 1);		
	}
	
	@Test
	public void testRemoveAnItem() {
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
		testSubject.addItem(SOUP_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 1);
		testSubject.addItem(SOUP_ITEM, 2);
		assertTrue("Basket has one product in it", testSubject.getContents().size() == 1);
		assertTrue("Just three tins", testSubject.getContents().get(SOUP.getName()).getQuantity() == 3);
		
		testSubject.removeItem(SOUP_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 1);
		assertTrue("Just two tins", testSubject.getContents().get(SOUP.getName()).getQuantity() == 2);
		
	}
	
	@Test
	public void testAddAndRomoveMultipleProducts() {
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
		testSubject.addItem(SOUP_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 1);
		testSubject.addItem(BREAD_ITEM, 3);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 2);
		testSubject.addItem(APPLES_ITEM, 10);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 3);	
		testSubject.removeItem(BREAD_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 3);	
		assertTrue("Just one tin of soup", testSubject.getContents().get(SOUP.getName()).getQuantity() == 1);
		assertTrue("Two loaves", testSubject.getContents().get(BREAD.getName()).getQuantity() == 2);
		assertTrue("Ten apples", testSubject.getContents().get(APPLES.getName()).getQuantity() == 10);
	}
	
	@Test
	public void testAddAndEmptyABasket() {
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
		testSubject.addItem(SOUP_ITEM, 1);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 1);
		testSubject.addItem(BREAD_ITEM, 3);
		assertTrue("Basket has an item in it", testSubject.getContents().size() == 2);
		testSubject.empty();
		assertTrue("Check basket is empty", testSubject.getContents().size() == 0);
	}
}
