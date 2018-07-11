package com.example.demo.controller.advice;

public class Response {

	private String errorCode;
	private String errorDesc;

	public Response(String string, String string2) {
		this.errorCode = string;
		this.errorDesc = string2;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
