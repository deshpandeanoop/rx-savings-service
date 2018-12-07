package com.rx.savings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rx.savings.idao.IRxSavingsDAO;
import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.response.PharmacyResponse;
@Service
public class RxSavingsService implements IRxSavingsService{
	private final Logger LOGGER = LoggerFactory.getLogger(RxSavingsService.class);
	private final IRxSavingsDAO rxSavingsDAO;
	public RxSavingsService(IRxSavingsDAO rxServiceDAO) {
		this.rxSavingsDAO = rxServiceDAO;
	}
	@Override
	public PharmacyResponse getNearestPharmacy(double userLocationLatitude, double userLocationLongitude) {
		LOGGER.debug("Calling Data Access layer to fech data");
		return rxSavingsDAO.getNearestPharmacy(userLocationLatitude, userLocationLongitude);
	}
}
