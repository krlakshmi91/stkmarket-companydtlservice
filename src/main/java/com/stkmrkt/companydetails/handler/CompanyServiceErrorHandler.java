package com.stkmrkt.companydetails.handler;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stkmrkt.companydetails.configuration.CompanyServiceErrorXMLParser;
import com.stkmrkt.companydetails.constants.Constants;
import com.stkmrkt.companydetails.exception.InputValidationException;
import com.stkmrkt.companydetails.model.CompanyServiceError;
import com.stkmrkt.companydetails.model.ErrorResponse;
import com.stkmrkt.companydetails.util.CompanyDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CompanyServiceErrorHandler {

	@Autowired
	CompanyDetailsUtil util;
	
	@Autowired
	CompanyServiceErrorXMLParser parser;
	
	@ExceptionHandler(value = InputValidationException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleInputValidationException(InputValidationException e) {
		log.error("handleInputValidationException, InputValidationException exception: ",e);
		CompanyServiceError error = parser.readErorMessage(e.getCode());
		if(null != error) {
			String errorMessage = !StringUtils.hasText(e.getMessage()) ? error.getErrorMessage():e.getMessage() +error.getErrorMessage();
			return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, MDC.get(Constants.REQUEST_ID), Integer.parseInt(error.getErrorCode()), errorMessage);
		}
		return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, MDC.get(Constants.REQUEST_ID),123, e.getMessage());
	}
	
	private ResponseEntity<ErrorResponse> buildErrorResponseEntity(HttpStatus httpstatus, String requestId,Integer errorCode,String errorMessage) {
		String responseId = util.getResponseId();
		log.error("Exception in Company Service : ", errorMessage);
		ErrorResponse response = new ErrorResponse();
		response.setRequestId(requestId);
		response.setResponseId(responseId);
		response.setErrorCode(errorCode);
		response.setErrorMessage(errorMessage);
		ResponseEntity<ErrorResponse> resp = new ResponseEntity<>(response, httpstatus);
		MDC.clear();
		return resp;
	}
}
