package com.ford.henrys.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.henrys.Basket;
import com.ford.henrys.BasketItem;
import com.ford.henrys.Discount;
import com.ford.henrys.TillReceiptItem;

@Service("priceCalculator")
public class PriceCalculatorImpl implements PriceCalculator {

	private Discounter discounter;
	
	private ConcurrentHashMap<String, Discount> discounts;	
	
	@Autowired
	public PriceCalculatorImpl(Discounter discounter) {
		this.discounter = discounter;
	}

	@Override
	public List<TillReceiptItem> calculateCost(Basket basket) {
		
		List<TillReceiptItem> receiptItems = new ArrayList<TillReceiptItem>();
		
		discounts = loadDiscounts(basket);
		
		for (BasketItem item : basket.getContents().values()) {
			TillReceiptItem tillReceiptItem = new TillReceiptItem();
			tillReceiptItem.setProduct(item.getStockItem().getProduct());
			tillReceiptItem.setGrossPrice(
					formatValue(item.getStockItem().getCost().multiply(
							new BigDecimal(item.getQuantity()))));
			tillReceiptItem.setQuantity(item.getQuantity());
			tillReceiptItem.setDiscount(calculateDiscount(item));
			
			receiptItems.add(tillReceiptItem);
		}
		
		return receiptItems;
	}

	private BigDecimal calculateDiscount(BasketItem item) {
		BigDecimal discountValue = new BigDecimal(0);		
		Discount discount = discounts.get(item.getStockItem().getProduct().getName());
		
		if (null != discount) {
			if (null != discount && 
				discount.getMaxItems() != null) {
				
				BigDecimal discountedValue = discount.getDiscount().multiply(
						item.getStockItem().getCost().multiply(
								new BigDecimal(discount.getMaxItems()))); 
				BigDecimal nonDiscountedValue = new BigDecimal(0);
				if (item.getQuantity() > discount.getMaxItems()) {
					int itemsNotDiscounted = item.getQuantity() - discount.getMaxItems();
			
					nonDiscountedValue.add(
							formatValue(item.getStockItem().getCost().multiply(
							new BigDecimal(itemsNotDiscounted))));
				}
				discountValue = discountedValue.add(nonDiscountedValue);
			}
			else {
				discountValue = 
					discount.getDiscount().multiply(
							item.getStockItem().getCost().multiply(
									new BigDecimal(item.getQuantity())));					
			}
		}
		return formatValue(discountValue);		
	}
	
	private ConcurrentHashMap<String, Discount> loadDiscounts(Basket basket) {
		ConcurrentHashMap<String, Discount> discounts = new ConcurrentHashMap<>();
		for (BasketItem item : basket.getContents().values()) {
			Discount discount = discounter.discount(item);
			if (null != discount) {
				discounts.put(discount.getProduct().getName(), discount);
			}
			
		}
		return discounts;
	}
	
	private BigDecimal formatValue(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}
}
