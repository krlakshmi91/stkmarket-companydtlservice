package com.stkmrkt.companydetails.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMPANY_PROFILE")
public class CompanyDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_CDE")
	private Long companyCode;
	
	@Column(name = "C_NAME")
	private String companyName;
	
	@Column(name = "C_CEO")
	private String companyCEO;
	
	@Column(name = "C_TRN_OVR")
	private BigDecimal companyTurnover;
	
	@Column(name = "C_WBSTE")
	private String companyWebsite;
	
	@Column(name = "C_XCHNGE")
	private String stockExchangeList;

	@Column(name = "CRT_TMS")
	private Date creationDate;
	
	@Column(name = "UPDT_TMS")
	private Date updateDate;
	
}
