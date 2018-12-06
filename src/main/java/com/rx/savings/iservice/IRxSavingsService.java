package com.rx.savings.iservice;

import com.rx.savings.response.PharmacyResponse;

public interface IRxSavingsService {
	public PharmacyResponse getNearestPharmacy(double userLocationLatitude,double userLocationLongitude);
}
