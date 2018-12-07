package com.rx.savings.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rx.savings.constants.RxSavingsErrConstants;
@RestController
public class RxSavingsErrorController implements ErrorController{
	private final String ERROR_PATH="/error";
	private final Logger LOGGER = LoggerFactory.getLogger(RxSavingsErrorController.class);
	@GetMapping(ERROR_PATH)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> sendErrorResponse(HttpRequest httpRequest){
		LOGGER.error("Cannot Serve the user request : "+httpRequest.getURI());
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", RxSavingsErrConstants.REQUEST_NOT_FOUND);
		return errorResponse;
	}
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
