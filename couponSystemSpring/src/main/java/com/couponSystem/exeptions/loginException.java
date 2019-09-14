package com.couponSystem.exeptions;

public class loginException extends Exception {
	String string;

	public loginException(String string) {
		this.string = string;
	}
}