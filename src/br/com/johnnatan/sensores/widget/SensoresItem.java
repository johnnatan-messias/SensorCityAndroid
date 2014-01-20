package br.com.johnnatan.sensores.widget;

import java.io.Serializable;
import java.util.Date;

public class SensoresItem implements Serializable {

	private static final long serialVersionUID = -3487852425658420880L;
	public static final String KEY = "SensoresItem";
	protected long id;
	private float atmPressure;
	private float audio;
	private float luminosity;
	private float temperature;
	private float humidity;
	private float latitude;
	private float longitude;
	private Address address;
	private String information;
	protected String name;
	protected Date timestamp;

	public SensoresItem() {
	}

	public float getLuminosity() {
		return this.luminosity;
	}

	public void setLuminosity(float luminosity) {
		this.luminosity = luminosity;
	}

	public void setLuminosity(String luminosity) {
		this.luminosity = Float.parseFloat(luminosity);
	}

	public float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = Float.parseFloat(temperature);
	}

	public float getHumidity() {
		return this.humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = Float.parseFloat(humidity);
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = Float.parseFloat(latitude);
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = Float.parseFloat(longitude);
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public float getAtmPressure() {
		return atmPressure;
	}

	public void setAtmPressure(float atmPressure) {
		this.atmPressure = atmPressure;
	}

	public float getAudio() {
		return audio;
	}

	public void setAudio(float audio) {
		this.audio = audio;
	}

}
