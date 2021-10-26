package com.stkmrkt.companydetails.exception;

@SuppressWarnings("serial")
public class InputValidationException extends RuntimeException {

	private String code;
	private String message;

	public InputValidationException() {
		super();
	}

	public InputValidationException(String code) {
		super("code: " + code);
		this.code = code;
	}

	public InputValidationException(String code, String message) {
		super("code: " + code);
		this.code = code;
		this.message = message;
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

	@Override
	public String toString() {
		return "InputValidationException [code=" + code + ", message=" + message + "]";
	}

}
