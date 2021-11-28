package com.stkmrkt.companydetails.model;

import java.util.List;

import lombok.Data;

@Data
public class StockDetailResponse extends SuccessResponse {

	private String id;
	private List<StockDetailEntity> stockDetails;
	private StockDetailEntity stockDetail;
	
}
