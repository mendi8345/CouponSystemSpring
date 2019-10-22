package com.couponSystem.utils;

import com.couponSystem.CouponClientFacade;

public class Session {

	private CouponClientFacade couponClient;
	private long lastAccessed;

	public CouponClientFacade getCouponClient() {
		return this.couponClient;
	}

	public void setCouponClient(CouponClientFacade couponClient) {
		this.couponClient = couponClient;
	}

	public long getLastAccessed() {
		return this.lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	@Override
	public String toString() {
		return "Session [couponClient=" + this.couponClient + ", lastAccessed=" + this.lastAccessed + "]";
	}
}