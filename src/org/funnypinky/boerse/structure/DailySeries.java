package org.funnypinky.boerse.structure;

public class DailySeries{

	private double open;
	private double high;
	private double low;
	private double close;
	private double adjustedClose;
	private double diviendeAmount;
	
	public DailySeries() {
		super();
	}
	
	public DailySeries(double open, double high, double low, double close, double adjustedClose,
			double diviendeAmount) {
		super();
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjustedClose = adjustedClose;
		this.diviendeAmount = diviendeAmount;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getAdjustedClose() {
		return adjustedClose;
	}
	public void setAdjustedClose(double adjustedClose) {
		this.adjustedClose = adjustedClose;
	}
	public double getDiviendeAmount() {
		return diviendeAmount;
	}
	public void setDiviendeAmount(double diviendeAmount) {
		this.diviendeAmount = diviendeAmount;
	}
	
	
}
