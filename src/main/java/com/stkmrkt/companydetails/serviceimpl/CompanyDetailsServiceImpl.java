package com.stkmrkt.companydetails.serviceimpl;

import static com.stkmrkt.companydetails.constants.Constants.COMPANY_CODE_EXISTS;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_STOCKLIST_ELIGIBILITY;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_TURNOVER_ELIGIBILITY;
import static com.stkmrkt.companydetails.constants.Constants.FINANCE_MARKET_LIST;
import static com.stkmrkt.companydetails.constants.Constants.INPUT_FIELD_REQUIRED;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stkmrkt.companydetails.entity.CompanyDetailsEntity;
import com.stkmrkt.companydetails.exception.InputValidationException;
import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.CompanyDetailsResponse;
import com.stkmrkt.companydetails.repository.CompanyDetailsRepository;
import com.stkmrkt.companydetails.service.CompanyDetailService;
import com.stkmrkt.companydetails.util.CompanyDetailResponseMapper;
import com.stkmrkt.companydetails.util.CompanyDetailsUtil;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailService {

	@Autowired
	CompanyDetailsRepository repository;
	
	@Autowired
	CompanyDetailsUtil util;
	
	@Autowired
	CompanyDetailResponseMapper mapper;

	@Override
	public CompanyDetailsResponse registerCompany(CompanyDetailsRequest request) {
		// Validate few parameters in the request
		validateCompanyCode(request.getCompanyCode(), request);
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		CompanyDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, false);

		// Call to DAO layer
		CompanyDetailsEntity result = repository.save(entity);

		// Form the response
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("Company registered successfully");

		return response;
	}
	
	private void validateCompanyCode(Long companyCode, CompanyDetailsRequest request) {
		CompanyDetailsEntity companyEntity = repository.findByCompanyCode(companyCode);
		
		if (null != companyEntity ) {
			if (companyEntity.getCompanyName().contentEquals(request.getCompanyName())) {
				throw new InputValidationException(COMPANY_CODE_EXISTS);
			}
		}
	}
	
	private void validateStockExchangeList(List<String> stockExchangeList) {
		if (stockExchangeList == null || stockExchangeList.isEmpty()) {
			throw new InputValidationException(INPUT_FIELD_REQUIRED, "stockExchangeList");
		}

		for (String financeMarket : stockExchangeList) {
			if (!FINANCE_MARKET_LIST.contains(financeMarket)) {
				throw new InputValidationException(COMPANY_STOCKLIST_ELIGIBILITY);
			}
		}

	}

	private void validateCompanyTurnOver(BigDecimal companyTurnOver) {
		if (companyTurnOver == null || companyTurnOver.compareTo(BigDecimal.ZERO) == 0) {
			throw new InputValidationException(INPUT_FIELD_REQUIRED, "companyTurnOver");
		}

		if (companyTurnOver.compareTo(new BigDecimal(100000000)) < 0) {
			throw new InputValidationException(COMPANY_TURNOVER_ELIGIBILITY);
		}

	}

}
