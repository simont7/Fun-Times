package com.ford.henrys.service;

import com.ford.henrys.Basket;
import com.ford.henrys.TillReceipt;

public interface Till {

	public TillReceipt checkout(Basket basket);
}
