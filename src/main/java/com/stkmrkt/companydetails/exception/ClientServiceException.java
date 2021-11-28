package com.stkmrkt.companydetails.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ClientServiceException extends RuntimeException {

	private String code;
	private String message;
	private HttpStatus status;

	public ClientServiceException() {
		super();
	}

	public ClientServiceException(String code) {
		super("code: " + code);
		this.code = code;
	}

	public ClientServiceException(String code, String message, HttpStatus status) {
		super("code: " + code);
		this.code = code;
		this.message = message;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClientServiceException [code=" + code + ", message=" + message + ", status=" + status + "]";
	}

}
