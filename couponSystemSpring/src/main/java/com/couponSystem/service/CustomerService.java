package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.javabeans.Customer;

public interface CustomerService {

	public Customer getCustomer() throws Exception;

	Customer purchaseCoupon(Coupon coupon) throws Exception;

	public List<Coupon> getAllPurchasedCoupon() throws Exception;

	public List<Coupon> getAllPurchasedCouponByType(CouponType couponType) throws Exception;

	public List<Coupon> getAllPurchasedCouponByPrice(double price) throws Exception;

	public void setCustomer(Customer customer);

}
