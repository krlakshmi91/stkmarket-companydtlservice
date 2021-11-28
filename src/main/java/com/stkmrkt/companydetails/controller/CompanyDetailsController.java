package com.stkmrkt.companydetails.controller;

import static com.stkmrkt.companydetails.constants.Constants.API_CONTEXT_ROOT;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_DETAILS_CONTROLLER;
import static com.stkmrkt.companydetails.constants.Constants.DELETE_COMPANY_URI;
import static com.stkmrkt.companydetails.constants.Constants.FETCHALL_COMPANY_DETAILS_URI;
import static com.stkmrkt.companydetails.constants.Constants.FETCH_COMPANY_DETAILS_URI;
import static com.stkmrkt.companydetails.constants.Constants.FETCH_COMPANY_STOCKS_BY_DATE_URI;
import static com.stkmrkt.companydetails.constants.Constants.REGISTER_COMPANY_URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@ApiOperation(value = FETCH_COMPANY_DETAILS_URI, notes = "This API fetches the comapny details for the requested company code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Company Data", response = CompanyDetailsResponse.class) })
	@GetMapping(value = FETCH_COMPANY_DETAILS_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> getCompanyDataByComapnyCode(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companyCode", value = "companyCode", required = true) @PathVariable(name="companyCode", required = true) String companyCode) {
		CompanyDetailsResponse response = service.getCompanyByCompanyCode(companyCode);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = FETCHALL_COMPANY_DETAILS_URI, notes = "This API fetches all the registered company's details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get all Company Data", response = CompanyDetailsResponse.class)})
	@GetMapping(value = FETCHALL_COMPANY_DETAILS_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header")})
	public ResponseEntity<CompanyDetailsResponse> getAllCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken) {
		CompanyDetailsResponse response = service.getAllCompanies();
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = DELETE_COMPANY_URI, notes = "This API deletes the Company details from  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Company", response = CompanyDetailsResponse.class) })
	@DeleteMapping(value = DELETE_COMPANY_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> deleteCompany(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companyCode", value = "companyCode", required = true) @PathVariable(name="companyCode", required = true) String companyCode) {
		CompanyDetailsResponse response = service.deleteComapany(companyCode);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = FETCH_COMPANY_STOCKS_BY_DATE_URI, notes = "This API fetches the stock value of the requested company by date")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Company Data", response = CompanyDetailsResponse.class) })
	@GetMapping(value = FETCH_COMPANY_STOCKS_BY_DATE_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> getStocksByRange(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companyCode", value = "companyCode", required = true) @PathVariable(name = "companyCode", required = true) String companyCode,
			@ApiParam(name = "startDate", value = "startDate", required = true) @PathVariable(name = "startDate", required = true) String startDate,
			@ApiParam(name = "endDate", value = "endDate", required = true) @PathVariable(name = "endDate", required = true) String endDate) {
		CompanyDetailsResponse response = service.getCompanyStocksByDate(companyCode, startDate, endDate);
		return ResponseEntity.ok(response);
	}
}
