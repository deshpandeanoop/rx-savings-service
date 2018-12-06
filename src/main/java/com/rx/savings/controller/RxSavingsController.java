package com.rx.savings.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	private Logger logger = LoggerFactory.getLogger(RxSavingsController.class);
	
	public RxSavingsController(IRxSavingsService rxService) {
		super();
		this.rxService = rxService;
	}
	@GetMapping("/pharmacy")	
	public PharmacyResponse getNearestRx(@Valid
			@Min(value=-90,message="latitude should be greater than -90 and less than 90")
			@Max(value=90,message="latitude should be greater than -90 and less than 90")
			@QueryParam("lattitude") double lattitude,
			@Valid
			@QueryParam("longitude") 
			@Min(value=-180,message="latitude should be greater than -180 and less than 180")
			@Max(value=180,message="latitude should be greater than -180 and less than 180")
			double longitude) {
		logger.debug("User Location : latitude-"+lattitude+"  longitude-"+longitude);
		return rxService.getNearestPharmacy(lattitude, longitude);
	}
}
