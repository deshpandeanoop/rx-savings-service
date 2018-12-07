package com.rx.savings.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rx.savings.constants.RxSavingsErrConstants;

@Component
@ControllerAdvice
public class RxSavingsExceptionHandler {
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Map<String, String> handle(NumberFormatException numberFormatException){
		Map<String, String> error = new HashMap<>();
		error.put("invalidParameters", RxSavingsErrConstants.INVALID_PARAMETERS);
		return error;
	}
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Map<String, List<String>> handle(ConstraintViolationException constraintViolationException){
		Map<String, List<String>> error = new HashMap<>();
		error.put("parametersOutOfRange",
		constraintViolationException
		.getConstraintViolations().stream()
		.map(violation->new StringBuffer().append("ReceivedInput : ")
															.append(violation.getInvalidValue())
															.append(" ; Message : ")
															.append(violation.getMessage()).toString()).collect(Collectors.toList()));
		return error;
	}	
}
