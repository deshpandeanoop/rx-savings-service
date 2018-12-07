package com.rx.savings.response;

public class PharmacyResponse {
	private final String pharmacyName;
	private final String address;
	private final double distance;
	public String getPharmacyName() {
		return pharmacyName;
	}
	public String getAddress() {
		return address;
	}
	public double getDistance() {
		return distance;
	}
	public PharmacyResponse(String pharmacyName,String address,double distance) {
		this.pharmacyName = pharmacyName;
		this.address = address;
		this.distance = distance;
	}
	
}
