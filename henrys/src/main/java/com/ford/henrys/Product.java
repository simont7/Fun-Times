package com.ford.henrys;

import java.util.Objects;

public class Product {

	private String name;
	private Unit unit;
	
	public Product(String name, Unit unit) {
		this.name = name;
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, unit);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name) && unit == other.unit;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", unit=" + unit + "]";
	}
	
}
