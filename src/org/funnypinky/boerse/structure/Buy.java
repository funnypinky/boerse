package org.funnypinky.boerse.structure;

public class Buy {

	private double amount;
	
	private double price;
	
	public Buy(double amount, double price) {
		this.amount = amount;
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getValue() {
		return price*amount;
	}
}
