package com.stkmrkt.companydetails.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.stkmrkt.companydetails.util.CompanyDetailResponseMapper;
import com.stkmrkt.companydetails.util.CompanyDetailsUtil;

@Configuration
public class CompanyDetailConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CompanyDetailsUtil companyDetailsUtil() {
		return new CompanyDetailsUtil();
	}
	
	@Bean
	public CompanyDetailResponseMapper companyDetailResponseMapper() {
		return new CompanyDetailResponseMapper();
	}
}
