package com.rx.savings.controller;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.response.PharmacyResponse;

@RestController
public class RxSavingsController {
	private final IRxSavingsService rxService;
	private final Logger logger = LoggerFactory.getLogger(RxSavingsController.class);
	
	public RxSavingsController(IRxSavingsService rxService) {
		super();
		this.rxService = rxService;
	}
	@GetMapping("/pharmacy")	
	public PharmacyResponse getNearestRx(
			@QueryParam("lattitude") double lattitude,
			@QueryParam("longitude") 
			double longitude) {
		logger.debug("User Location : latitude-"+lattitude+"  longitude-"+longitude);
		return rxService.getNearestPharmacy(lattitude, longitude);
	}
}
