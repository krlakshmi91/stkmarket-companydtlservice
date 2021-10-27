package com.stkmrkt.companydetails.model;

import static com.stkmrkt.companydetails.constants.Constants.ALPHA_NUMERIC_REGEX;
import static com.stkmrkt.companydetails.constants.Constants.ALPHA_REGEX;
import static com.stkmrkt.companydetails.constants.Constants.NUMERIC_DECIMEL_REGEX;
import static com.stkmrkt.companydetails.constants.Constants.STRING_Y;
import static com.stkmrkt.companydetails.constants.Constants.WEBSITE_REGEX;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.stkmrkt.companydetails.handler.InputValidateData;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CompanyDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long companyCode;

	@InputValidateData(required = STRING_Y, regex = ALPHA_NUMERIC_REGEX, max = 255)
	private String companyName;

	@InputValidateData(required = STRING_Y, regex = ALPHA_REGEX, max = 255)
	private String companyCEO;

	@InputValidateData(required = STRING_Y, regex = NUMERIC_DECIMEL_REGEX)
	private BigDecimal companyTurnover;

	@InputValidateData(required = STRING_Y, regex = WEBSITE_REGEX)
	private String companyWebsite;

	private List<String> stockExchangeList;

}
