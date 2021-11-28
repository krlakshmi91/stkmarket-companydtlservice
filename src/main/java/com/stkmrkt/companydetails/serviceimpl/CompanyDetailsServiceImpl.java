package com.stkmrkt.companydetails.serviceimpl;

import static com.stkmrkt.companydetails.constants.Constants.COMPANY_CODE_EXISTS;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_STOCKLIST_ELIGIBILITY;
import static com.stkmrkt.companydetails.constants.Constants.COMPANY_TURNOVER_ELIGIBILITY;
import static com.stkmrkt.companydetails.constants.Constants.FINANCE_MARKET_LIST;
import static com.stkmrkt.companydetails.constants.Constants.INPUT_FIELD_REQUIRED;
import static com.stkmrkt.companydetails.constants.Constants.INVALID_COMPANY_CODE;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stkmrkt.companydetails.client.StockServiceClient;
import com.stkmrkt.companydetails.entity.CompanyDetailsEntity;
import com.stkmrkt.companydetails.exception.InputValidationException;
import com.stkmrkt.companydetails.model.CompanyDetail;
import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.CompanyDetailsResponse;
import com.stkmrkt.companydetails.model.StockDetailEntity;
import com.stkmrkt.companydetails.model.StockDetailResponse;
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

	@Autowired
	StockServiceClient client;

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

		if (null != companyEntity) {
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

	@Override
	public CompanyDetailsResponse editComapany(CompanyDetailsRequest request) {
		// Validate few parameters in the request
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		CompanyDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, true);

		// Call to DAO layer
		CompanyDetailsEntity result = repository.save(entity);

		// Form the response
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("Company details updated successfully");

		return response;
	}

	@Override
	public CompanyDetailsResponse deleteComapany(String companyCode) {

		Long companyId = Long.valueOf(companyCode);
		boolean companyExists = repository.existsById(companyId);

		if (!companyExists) {
			throw new InputValidationException(INVALID_COMPANY_CODE);
		}

		client.deleteStocksOfComapny(companyCode);
		repository.deleteById(companyId);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("Company deleted successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getCompanyByCompanyCode(String companyCode) {
		Long companyId = Long.valueOf(companyCode);

		CompanyDetailsEntity result = repository.findById(companyId)
				.orElseThrow(() -> new InputValidationException(INVALID_COMPANY_CODE));
		StockDetailResponse stockResult = client.getStockByCompanyCode(companyCode);

		CompanyDetailsResponse response = new CompanyDetailsResponse();
		CompanyDetail companyDetail = mapper.companyDetailsResponseMapper(result, stockResult.getStockDetails());
		response.setResponseID(util.getResponseId());
		response.setCompanyDetail(companyDetail);
		response.setResponseMsg("Fetched company data successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getAllCompanies() {
		List<CompanyDetailsEntity> result = repository.findAll();
		StockDetailResponse stockResult = client.getAllCompanyStocks();

		CompanyDetailsResponse response = new CompanyDetailsResponse();
		List<CompanyDetail> companyDetails = mapper.getAllCompanyResponseMapper(result, stockResult);
		response.setResponseID(util.getResponseId());
		response.setCompanyDetails(companyDetails);
		response.setResponseMsg("Fetched All company details successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getCompanyStocksByDate(String companyCode, String startDate, String endDate) {
		Long companyId = Long.valueOf(companyCode);

		CompanyDetailsEntity result = repository.findById(companyId)
				.orElseThrow(() -> new InputValidationException(INVALID_COMPANY_CODE));
		StockDetailResponse stockResult = client.getStocksByRange(companyCode, startDate, endDate);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		CompanyDetail companyDetail = mapper.companyDetailsResponseMapper(result, stockResult.getStockDetails());
		
		if (stockResult != null && stockResult.getStockDetails() != null && !stockResult.getStockDetails().isEmpty()) {
			companyDetail.setMaxStockPrice(stockResult.getStockDetails().stream()
					.mapToDouble(StockDetailEntity::getStockPrice).max().getAsDouble());
			companyDetail.setMinStockPrice(stockResult.getStockDetails().stream()
					.mapToDouble(StockDetailEntity::getStockPrice).min().getAsDouble());
			companyDetail.setAvgStockPrice(stockResult.getStockDetails().stream()
					.collect(Collectors.averagingDouble(StockDetailEntity::getStockPrice)));
		}

		response.setResponseID(util.getResponseId());
		response.setCompanyDetail(companyDetail);
		response.setResponseMsg("Company Stock fetched by date successfully");
		return response;
	}

}
