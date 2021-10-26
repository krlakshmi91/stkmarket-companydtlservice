package com.stkmrkt.companydetails.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "companyservice-errors")
public class CompanyServiceErrors {

	private List<CompanyServiceError> companyServiceErrorList;

	public List<CompanyServiceError> getCompanyServiceErrorList() {
		return companyServiceErrorList;
	}

	@XmlElement(name = "companyservice-error")
	public void setCompanyServiceErrorList(List<CompanyServiceError> companyServiceErrorList) {
		this.companyServiceErrorList = companyServiceErrorList;
	}

	@Override
	public String toString() {
		return "CompanyServiceErrors [companyServiceErrorList=" + companyServiceErrorList + "]";
	}

}
