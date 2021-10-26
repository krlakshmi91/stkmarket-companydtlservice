package com.stkmrkt.companydetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Data;

@Data
@JsonRootName(value = "ErrorResponse")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
public class ErrorResponse {

	@JsonProperty(required = true, value = "responseId")
	private String responseId;
	@JsonProperty(required = true, value = "requestId")
	private String requestId;
	@JsonProperty(required = true, value = "errorCode")
	private Integer errorCode;
	@JsonProperty(required = true, value = "errorMessage")
	private String errorMessage;
}
