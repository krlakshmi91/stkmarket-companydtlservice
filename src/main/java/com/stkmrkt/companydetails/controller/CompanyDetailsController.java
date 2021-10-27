package com.stkmrkt.companydetails.controller;

import static com.stkmrkt.companydetails.constants.Constants.API_CONTEXT_ROOT;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_DETAILS_CONTROLLER;
import static com.stkmrkt.companydetails.constants.Constants.REGISTER_COMPANY_URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.CompanyDetailsResponse;
import com.stkmrkt.companydetails.service.CompanyDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = API_CONTEXT_ROOT, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = COMPANY_DETAILS_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CompanyDetailsController {
	
	@Autowired
	CompanyDetailService service;
	
	@ApiOperation(value = REGISTER_COMPANY_URI, notes = "This API registers company and stores details in  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Register Company", response = CompanyDetailsResponse.class) })
	@PostMapping(REGISTER_COMPANY_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> registerCompany(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "CompanyDetailsRequest", value = "Request Body", required = true) @RequestBody @Valid CompanyDetailsRequest request) {
		log.info("Company Detail started");
		CompanyDetailsResponse response = service.registerCompany(request);
		log.info("Company Detail ended");
		return ResponseEntity.ok(response);
	}

}
