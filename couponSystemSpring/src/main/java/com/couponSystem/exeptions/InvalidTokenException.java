package com.couponSystem.exeptions;

public class InvalidTokenException extends Exception {

	private String token;

	public InvalidTokenException(String message, String token) {

		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

}
