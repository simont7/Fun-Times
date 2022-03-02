package com.ford.henrys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TillReceipt {
	
	private List<TillReceiptItem> entries;
	
	public List<TillReceiptItem> getEntries() {
		if (null == entries) {
			entries = new ArrayList<TillReceiptItem>();
		}
		return entries;
	}
	public void setEntry(List<TillReceiptItem> entries) {
		this.entries = entries;
	}
	public BigDecimal getPrice() {
		
		BigDecimal price = new BigDecimal(0);
		
		for (TillReceiptItem receiptItem : getEntries()) {
			price = price.add(receiptItem.getGrossPrice());
		}
		return price;
	}

	public BigDecimal getDiscount() {
		BigDecimal discount = new BigDecimal(0);
		
		for (TillReceiptItem receiptItem : getEntries()) {
			discount = discount.add(receiptItem.getDiscount());				
		}
		return discount;
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		
		for (TillReceiptItem receiptItem : getEntries()) {
			total = total.add(receiptItem.getToPay());				
		}
		return total;
	}
	
	public String toString() {
		StringBuffer receipt = new StringBuffer("Henrys Groceries\n");
		for (TillReceiptItem item : getEntries()) {
			receipt.append(item.getProduct().getName() + " *  " +
					item.getQuantity() + " : " + 
					item.getToPay() + " \n");
		}
		receipt.append("Total : " + getTotal()); 
		
		
		return receipt.toString();
	}
}
