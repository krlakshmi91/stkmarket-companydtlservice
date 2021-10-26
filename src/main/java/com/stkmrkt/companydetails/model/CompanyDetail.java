package com.stkmrkt.companydetails.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CompanyDetail {

	private Long companyCode;
	private String companyName;
	private String companyCEO;
	private BigDecimal companyTurnover;
	private String companyWebsite;
	private String stockExchangeList;
	private Double maxStockPrice;
	private Double minStockPrice;
	private Double avgStockPrice;
	private List<StockDetails> stockDetails;

}
