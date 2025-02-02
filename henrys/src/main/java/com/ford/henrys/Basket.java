package com.ford.henrys;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Basket - represents a shopping basket 
 * @author snjohnson
 *
 */
public class Basket {
	
	/**
	 * contents - basket contents
	 */
    private HashMap<String, BasketItem> contents;
	
    /**
     * addItem - Adds an item for today
     * @param stockItem
     * @param quantity
     */
	public void addItem(StockItem stockItem, int quantity) {
		addItem(stockItem, quantity, LocalDate.now());
	}
	
	/**
	 * addItem - Adds an item to the basket for the date specified
	 * @param stockItem
	 * @param quantity
	 * @param dateAdded
	 */
	public void addItem(StockItem stockItem, int quantity, LocalDate dateAdded) {
		if (contents == null) {
			contents = new HashMap<String, BasketItem>();
		}
		BasketItem basketItem = findItem(stockItem.getProduct().getName());
		
		if (null != basketItem) {
			basketItem.setQuantity(basketItem.getQuantity() + quantity);
			contents.replace(stockItem.getProduct().getName(), basketItem);			
		}
		else {
			contents.put(
				stockItem.getProduct().getName(),
				new BasketItem(stockItem, quantity, dateAdded));
		}
	}
	
	private BasketItem findItem(String name) {
		return contents.get(name);
	}
	
	/**
	 * removeItem - Removes an item from the basket
	 * @param stockItem
	 * @param quantity
	 */
	public void removeItem(StockItem stockItem, int quantity) {
		if (contents != null) {
			String key = stockItem.getProduct().getName();
			BasketItem basketItem = findItem(key);
			
			if (null != basketItem) {
				basketItem.setQuantity(basketItem.getQuantity() - quantity);
				
				if (basketItem.getQuantity() > 0) {
					contents.replace(key, basketItem);	
				}
				else {
					contents.remove(key);
				}
			}

		}	
	}
	
	public void empty() {
		contents = null;
	}
	/*
	 * getContents - returns the contents of the basket
	 */
	public HashMap<String, BasketItem> getContents() {
		if (null == contents) {
			contents = new HashMap<>();
		}
		return contents;
	}		
}
