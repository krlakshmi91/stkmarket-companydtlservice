package com.stkmrkt.companydetails.controller;

import static com.stkmrkt.companydetails.constants.Constants.API_CONTEXT_ROOT;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_DETAILS_CONTROLLER;
import static com.stkmrkt.companydetails.constants.Constants.REGISTER_COMPANY_URI;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stkmrkt.companydetails.model.CompanyDetailsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = API_CONTEXT_ROOT, produces = { MediaType.APPLICATION_JSON_VALUE, }, consumes = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(value = COMPANY_DETAILS_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CompanyDetailsController {
	
	@ApiOperation(value = REGISTER_COMPANY_URI, notes = "This API stores the company details in  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Save Company Data", response = CompanyDetailsResponse.class) })
	@PostMapping(REGISTER_COMPANY_URI)
	//@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public void registerCompany() {
		
	}

}
