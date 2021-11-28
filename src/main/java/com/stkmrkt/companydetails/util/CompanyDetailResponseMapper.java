package com.stkmrkt.companydetails.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stkmrkt.companydetails.entity.CompanyDetailsEntity;
import com.stkmrkt.companydetails.model.CompanyDetail;
import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.StockDetailEntity;
import com.stkmrkt.companydetails.model.StockDetailResponse;
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
		if (updateFlag) {
			entity.setUpdateDate(new Date());
		} else {
			entity.setCreationDate(new Date());
			entity.setUpdateDate(new Date());
		}
		return entity;
	}

	public CompanyDetail companyDetailsResponseMapper(CompanyDetailsEntity entity,
			List<StockDetailEntity> stockResponse) {
		CompanyDetail response = new CompanyDetail();
		response.setCompanyCode(entity.getCompanyCode());
		response.setCompanyCEO(entity.getCompanyCEO());
		response.setCompanyName(entity.getCompanyName());
		response.setCompanyTurnover(entity.getCompanyTurnover());
		response.setCompanyWebsite(entity.getCompanyWebsite());
		response.setStockExchangeList(entity.getStockExchangeList());

		List<StockDetails> stockDetails = new ArrayList<>();

		for (StockDetailEntity stocks : stockResponse) {
			StockDetails stockDetail = new StockDetails();
			stockDetail.setStockPrice(stocks.getStockPrice());
			stockDetail.setStockUpdtTms(stocks.getStockUpdtTms());
			stockDetails.add(stockDetail);
		}

		response.setStockDetails(stockDetails);
		return response;
	}

	public List<CompanyDetail> getAllCompanyResponseMapper(List<CompanyDetailsEntity> entities,
			StockDetailResponse stockResponse) {

		List<CompanyDetail> compantDetails = new ArrayList<>();
		for (CompanyDetailsEntity companyDetailsEntity : entities) {
			List<StockDetailEntity> stocks = stockResponse.getStockDetails().stream()
					.filter(s -> s.getCompanyCode() == companyDetailsEntity.getCompanyCode())
					.collect(Collectors.toList());
			CompanyDetail detail = companyDetailsResponseMapper(companyDetailsEntity, stocks);
			if (stocks != null && !stocks.isEmpty()) {
				detail.setMaxStockPrice(
						stocks.stream().mapToDouble(StockDetailEntity::getStockPrice).max().getAsDouble());
				detail.setMinStockPrice(
						stocks.stream().mapToDouble(StockDetailEntity::getStockPrice).min().getAsDouble());
				detail.setAvgStockPrice(
						stocks.stream().collect(Collectors.averagingDouble(StockDetailEntity::getStockPrice)));
			}
			compantDetails.add(detail);
		}
		return compantDetails;
	}

}
