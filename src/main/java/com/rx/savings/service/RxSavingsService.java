package com.rx.savings.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.model.Pharmacy;
import com.rx.savings.repository.RxSavingsRepository;
import com.rx.savings.response.PharmacyResponse;
@Service
public class RxSavingsService implements IRxSavingsService{
	@Autowired
	private  RxSavingsRepository rxSavingsRepository;
	private Logger logger = LoggerFactory.getLogger(RxSavingsService.class);
	@Override
	public PharmacyResponse getNearestPharmacy(double userLocationLatitude, double userLocationLongitude) {
		List<Pharmacy> pharmacies = rxSavingsRepository.findAll();
		logger.debug("Fetched data from data base successfully, total records fetched "+pharmacies.size());
		double nearestDistance = Double.MAX_VALUE;
		double calculatedDistance;
		Pharmacy nearestPharmacy = new Pharmacy();
		for(Pharmacy rx: pharmacies) {
			calculatedDistance = getDistance(userLocationLatitude, userLocationLongitude, rx.getLatitude(), rx.getLongitude());
			if(calculatedDistance<nearestDistance) {
				nearestDistance = calculatedDistance;
				nearestPharmacy = rx;
			}
		}
		logger.debug("Nearest Pharmacy Name : "+nearestPharmacy.getName());
		logger.debug("Nearest Pharmacy Address : "+nearestPharmacy.getAddress());
		logger.debug("Nearest Pharmacy Distance : "+nearestDistance);
		return new PharmacyResponse(nearestPharmacy.getName(), nearestPharmacy.getAddress(), Math.round(nearestDistance));
	}
	/**
	 * Calculates distance between two points for given latitude and longitude values in miles using Haversine formula
	 * @param usrLocLatitude - user current location latitude
	 * @param usrLocLongitude - user current location longitude
	 * @param rxLatitude - pharmacy location latitude 
	 * @param rxLongitude -  pharmacy location longitude
	 * @return
	 */
	private double getDistance(double usrLocLatitude,double usrLocLongitude,double rxLatitude,double rxLongitude) {
		logger.debug("Calculating distance for following latitute and longitude pair");
		logger.debug("userLat: "+usrLocLatitude);
		logger.debug("pharmacy latitude "+rxLatitude);
		logger.debug("userLongitude: "+usrLocLongitude);
		logger.debug("pharmacy longitude "+rxLongitude);
		int earthRadiusInMiles = 3959;
		double latDistance = degreeToRadian(rxLatitude - usrLocLatitude);
		double longDistance = degreeToRadian(rxLongitude-usrLocLongitude);
		double temp = Math.sin(latDistance/2) * Math.sin(latDistance)/2 
				+Math.cos(degreeToRadian(usrLocLatitude))*Math.cos(degreeToRadian(rxLatitude))*
				Math.sin(longDistance/2) * Math.sin(longDistance/2);
		double temp1 = 2*Math.atan2(Math.sqrt(temp),Math.sqrt(1-temp));
		return (temp1*earthRadiusInMiles);
	}
	private double degreeToRadian(double degree) {
		return degree*(Math.PI/180);
	}
}
