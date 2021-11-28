package com.stkmrkt.companydetails.client;

import com.stkmrkt.companydetails.model.StockDetailResponse;

public interface StockServiceClient {
	public StockDetailResponse deleteStocksOfComapny(String companyCode);

	public StockDetailResponse getAllCompanyStocks();

	public StockDetailResponse getStockByCompanyCode(String companyCode);

	public StockDetailResponse getStocksByRange(String companyCode, String startDate, String endDate);
}
