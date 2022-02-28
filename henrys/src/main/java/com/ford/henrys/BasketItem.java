package com.ford.henrys;

import java.time.LocalDate;

public class BasketItem {
	
	private StockItem stockItem;
	private int quantity;
	private LocalDate dateAdded;
	
	public BasketItem(StockItem stockItem, int quantity) {
		this.stockItem = stockItem;
		this.quantity = quantity;
		dateAdded = LocalDate.now();
	}
	
	public BasketItem(StockItem stockItem, int quantity, LocalDate dateAdded) {
		this.stockItem = stockItem;
		this.quantity = quantity;
		this.dateAdded = dateAdded;
	}
	
	public StockItem getStockItem() {
		return stockItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDateAdded() {
		return dateAdded;
	}

}
