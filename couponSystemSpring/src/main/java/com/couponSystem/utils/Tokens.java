package com.couponSystem.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component

public class Tokens {
	private Map<String, Session> tokens = new HashMap<>();

	public Tokens() {

	}

	public Map<String, Session> getTokens() {
		return this.tokens;
	}

	public void setTokens(Map<String, Session> tokens) {
		this.tokens = tokens;
	}

	@Override
	public String toString() {
		return "Tokens [tokens=" + this.tokens + "]";
	}
}