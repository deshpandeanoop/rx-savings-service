package com.rx.savings.constants;

public class RxSavingsErrConstants {
	public static final String OUT_OF_RANGE_LATITUDE_VALUE = "Latitude value should be less than or equals to 90 and greater than or equals to -90";
	public static final String OUT_OF_RANGE_LONGITUDE_VALUE = "Longitude value should be less than or equals to 180 and greater than or equals to -180";
	public static final String REQUEST_NOT_FOUND="Endpoint doesn't exists, please try (/pharmacy) with latitude and longitude as query parameters. Ex: /pharmacy?latitude=22.123&longitude=-98.243";
	public static final String INVALID_PARAMETERS = "Latitude,Longitude values must be of double, in Range:[-90,90], [-180,180]";
}
