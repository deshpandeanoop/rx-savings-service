package com.rx.savings.idao;

import com.rx.savings.response.PharmacyResponse;

public interface IRxSavingsDAO {
	public PharmacyResponse getNearestPharmacy(double latitude,double longitude);
}
