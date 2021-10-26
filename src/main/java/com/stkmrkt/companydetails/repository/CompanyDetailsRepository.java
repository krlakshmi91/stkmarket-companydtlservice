package com.stkmrkt.companydetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stkmrkt.companydetails.entity.CompanyDetailsEntity;

public interface CompanyDetailsRepository extends JpaRepository<CompanyDetailsEntity, Long>{

}
