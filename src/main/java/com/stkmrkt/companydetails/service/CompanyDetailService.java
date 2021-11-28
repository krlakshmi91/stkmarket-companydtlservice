package com.stkmrkt.companydetails.service;

import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.CompanyDetailsResponse;

public interface CompanyDetailService {
	
	public CompanyDetailsResponse registerCompany(CompanyDetailsRequest request);
	
	public CompanyDetailsResponse editComapany(CompanyDetailsRequest request);
	
	public CompanyDetailsResponse deleteComapany(String companyCode);

	public CompanyDetailsResponse getCompanyByCompanyCode(String companyCode);

	public CompanyDetailsResponse getAllCompanies();

	public CompanyDetailsResponse getCompanyStocksByDate(String companyCode, String startDate, String endDate);

}
