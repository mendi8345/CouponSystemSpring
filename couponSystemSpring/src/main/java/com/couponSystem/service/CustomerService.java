package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;

public interface CustomerService {
	public void purchaseCoupon(Coupon coupon) throws Exception;

	public List<Coupon> getAllPurchasedCoupon() throws Exception;

	public List<Coupon> getAllPurchasedCouponByType(CouponType couponType) throws Exception;

	public List<Coupon> getAllPurchasedCouponByPrice(double price) throws Exception;
}
