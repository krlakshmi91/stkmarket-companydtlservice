package com.stkmrkt.companydetails.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stkmrkt.companydetails.entity.CompanyDetailsEntity;
import com.stkmrkt.companydetails.model.CompanyDetail;
import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.StockDetails;

public class CompanyDetailResponseMapper {

	public CompanyDetailsEntity companyDeatilsRequestMapper(CompanyDetailsRequest request, boolean updateFlag) {
		CompanyDetailsEntity entity = new CompanyDetailsEntity();
		entity.setCompanyCode(request.getCompanyCode());
		entity.setCompanyCEO(request.getCompanyCEO());
		entity.setCompanyName(request.getCompanyName());
		entity.setCompanyTurnover(request.getCompanyTurnover());
		entity.setCompanyWebsite(request.getCompanyWebsite());
		entity.setStockExchangeList(request.getStockExchangeList().stream().collect(Collectors.joining(",")));
		if(updateFlag) 
			entity.setUpdateDate(new Date());
		else
			entity.setCreationDate(new Date());
		return entity;
	}
	
	public CompanyDetail companyDetailsResponseMapper(
			CompanyDetailsEntity entity/* , List<StockDetailEntity> stockResponse */) {
		CompanyDetail response = new CompanyDetail();
		response.setCompanyCode(entity.getCompanyCode());
		response.setCompanyCEO(entity.getCompanyCEO());
		response.setCompanyName(entity.getCompanyName());
		response.setCompanyTurnover(entity.getCompanyTurnover());
		response.setCompanyWebsite(entity.getCompanyWebsite());
		response.setStockExchangeList(entity.getStockExchangeList());
		
		List<StockDetails> stockDetails = new ArrayList<>();
		/*
		 * for (StockDetailEntity stocks : stockResponse) { StockDetails stockDetail =
		 * new StockDetails(); stockDetail.setStockPrice(stocks.getStockPrice());
		 * stockDetail.setStockUpdtTms(stocks.getStockUpdtTms());
		 * stockDetails.add(stockDetail); }
		 */
		
		response.setStockDetails(stockDetails);
		return response;
	}
	
}
