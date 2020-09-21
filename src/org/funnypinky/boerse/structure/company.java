package org.funnypinky.boerse.structure;

import java.util.Date;
import java.util.HashMap;

public class Company {
	
	private String name;
	private String currency;
	private String country;
	private String sector;
	private double bookvalue;
	private double diviende;
	private double divienderendite;
	
	private final HashMap<Date, Bookdata> history = new HashMap<>();
	
	public Company(String name) {
		super();
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

	public HashMap<Date, Bookdata> getHistory() {
		return history;
	}
	
	

}
