package com.couponSystem.utils;

import org.springframework.stereotype.Component;

@Component
public class Token {
	private String token;
	private String clientType;

	public Token() {
	}

	public String getToken() {
		return this.token;
	}

	public String getClientType() {
		return this.clientType;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

}
