package org.funnypinky.boerse.structure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class company {

	private final String symbol;
	private String name;
	private String currency;
	private String country;
	private String sector;
	private double bookvalue;
	private double diviende;
	private double divienderendite;
	
	private HashMap<LocalDate, DailySeries> seriesDaily = new HashMap<>(); 

	private final HashMap<LocalDate, Bookdata> history = new HashMap<>();

	public company(String symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public double getBookvalue() {
		return bookvalue;
	}

	public void setBookvalue(double bookvalue) {
		this.bookvalue = bookvalue;
	}

	public double getDiviende() {
		return diviende;
	}

	public void setDiviende(double diviende) {
		this.diviende = diviende;
	}

	public double getDivienderendite() {
		return divienderendite;
	}

	public void setDivienderendite(double divienderendite) {
		this.divienderendite = divienderendite;
	}

	public HashMap<LocalDate, Bookdata> getHistory() {
		return history;
	}

	public String getSymbol() {
		return symbol;
	}
	

	public HashMap<LocalDate, DailySeries> getSeriesDaily() {
		return seriesDaily;
	}

	public void setSeriesDaily(HashMap<LocalDate, DailySeries> seriesDaily) {
		this.seriesDaily = seriesDaily;
	}

	public Double getLastPrice() {
		List<LocalDate> time = new ArrayList<LocalDate>(this.seriesDaily.keySet());
		Collections.sort(time);
		DailySeries value = this.seriesDaily.get(time.get(0));
		return value.getAdjustedClose();
	}
	
	
	@Override
	public String toString() {
		StringBuilder line = new StringBuilder();
		line.append(name).append(" (Symbol: ").append(symbol).append(")");
		return line.toString();
	}

}
