package com.rx.savings.controller;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rx.savings.constants.RxSavingsErrConstants;
import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.response.PharmacyResponse;

@RestController
@Validated
public class RxSavingsController {
	private final IRxSavingsService rxService;
	private final Logger LOGGER = LoggerFactory.getLogger(RxSavingsController.class);
	
	public RxSavingsController(IRxSavingsService rxService) {
		super();
		this.rxService = rxService;
	}
	@GetMapping("/pharmacy")	
	public PharmacyResponse getNearestRx(
			@Min(value=-90,message=RxSavingsErrConstants.OUT_OF_RANGE_LATITUDE_VALUE)
			@Max(value=90,message=RxSavingsErrConstants.OUT_OF_RANGE_LATITUDE_VALUE)
			@QueryParam("latitude") double latitude,
			@Min(value=-180,message=RxSavingsErrConstants.OUT_OF_RANGE_LONGITUDE_VALUE)
			@Max(value=180,message=RxSavingsErrConstants.OUT_OF_RANGE_LONGITUDE_VALUE)
			@QueryParam("longitude") 
			double longitude) {
		LOGGER.debug("All validations are passed, request started processing");
		return rxService.getNearestPharmacy(latitude, longitude);
	}
}
