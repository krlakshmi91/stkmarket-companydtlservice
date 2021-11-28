package com.stkmrkt.companydetails.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final String API_CONTEXT_ROOT = "companydetails/v1.0/market";
	public static final String REGISTER_COMPANY_URI = "/company/register";
	public static final String FETCH_COMPANY_DETAILS_URI = "/company/info/{companyCode}";
	public static final String FETCHALL_COMPANY_DETAILS_URI = "/company/getAll";
	public static final String UPDATE_COMPANY_STOCK_URI = "/stock/add/{companyCode}";
	public static final String FETCH_COMPANY_STOCKS_BY_DATE_URI = "/stock/get/{companyCode}/{startDate}/{endDate}";
	public static final String DELETE_COMPANY_URI = "/company/delete/{companyCode}";
	public static final String COMPANY_DETAILS_CONTROLLER = "COMPANY DETAILS CONTROLLER";
	
	public static final String REQUEST_ID = "requestId";
	public static final String STRING_Y = "Y";
	public static final String STRING_N = "N";
	
	public static final String ALPHA_REGEX = "^[a-zA-Z]+$";
	public static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9]+$";
	public static final String NUMERIC_REGEX = "^[+-]?[0-9]*$";
	public static final String NUMERIC_DECIMEL_REGEX = "^[+-]?[0-9]*\\.?[0-9]*$";
	public static final String WEBSITE_REGEX = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";
	
	public static final String INPUT_FIELD_REQUIRED = "field.required";
	public static final String INPUT_FIELD_INVALID = "field.invalid";
	public static final String INPUT_FIELD_INVALID_NOTALLOWED = "field.invalid.notallowed";
	public static final String INPUT_FIELD_LENGTH_INVALID = "field.invalid.length";
	public static final String DOWNSTREAM_ERROR = "downstream.error";
	public static final String DOWNSTREAM_NULL_RESPONSE = "downstream.nullresponse";
	public static final String COMPANY_TURNOVER_ELIGIBILITY = "company.turnover.eligibility";
	public static final String COMPANY_STOCKLIST_ELIGIBILITY = "company.stocklist.eligibility";
	public static final String COMPANY_CODE_EXISTS = "company.code.exists";
	public static final String INVALID_COMPANY_CODE = "invalid.companycode";
	
	public static final List<String> FINANCE_MARKET_LIST = Arrays.asList("NSE","BSE");
	
	public static final String COMPANY_SERVICE_ERROR_XML_FILE_PATH = "/companyservice-errors.xml";

}
