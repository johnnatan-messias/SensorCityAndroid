package br.com.johnnatan.sensores.widget;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 6439126773316342646L;
	private String street;
	private int number;
	private String neighborhood;
	private String city;
	private String state;
	private String country;

	public Address(String street, int number, String neighborhood, String city,
			String state, String country) {
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Address() {
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setNumber(String number) {
		this.number = Integer.parseInt(number);
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
