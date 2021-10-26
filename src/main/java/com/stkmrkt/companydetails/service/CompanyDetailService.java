package com.stkmrkt.companydetails.service;

import com.stkmrkt.companydetails.model.CompanyDetailsRequest;
import com.stkmrkt.companydetails.model.CompanyDetailsResponse;

public interface CompanyDetailService {
	
	public CompanyDetailsResponse registerCompany(CompanyDetailsRequest request);

}
