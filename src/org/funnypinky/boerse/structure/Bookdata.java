package org.funnypinky.boerse.structure;

import java.util.Date;

public class Bookdata {

	private final Date date;
	
	private double open;
	private double close;
	private double low;
	private double high;
	private double dividendAmount;
	
	public Bookdata(Date date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getDividendAmount() {
		return dividendAmount;
	}

	public void setDividendAmount(double dividendAmount) {
		this.dividendAmount = dividendAmount;
	}

	public Date getDate() {
		return date;
	}
	
	
	
}
