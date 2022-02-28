package com.ford.henrys;

public enum Unit {
	TIN ("tin"),
	LOAF ("loaf"),
	BOTTLE ("bottle"),
	SINGLE ("single");

	private String name;
	
	Unit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
