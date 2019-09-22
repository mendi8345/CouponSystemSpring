package com.couponSystem.exeptions;

public class CouponNotAvailableException extends Exception {
	String string;

	public CouponNotAvailableException(String string) {
		this.string = string;
	}
}
