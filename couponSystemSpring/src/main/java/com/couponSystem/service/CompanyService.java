package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;

public interface CompanyService {

	public Company getCompany();

	public void createCoupon(Coupon coupon) throws Exception;

	public void removeCoupon(long id) throws Exception;

	public void updateCoupon(Coupon coupon) throws Exception;

	public Coupon getCoupon(long id) throws Exception;

	public List<Coupon> getCompCoupons() throws Exception;

	public List<Coupon> getCouponsByType(CouponType couponType) throws Exception;

	public void setCompany(Company company);

	public List<Coupon> getCouponsByPrice(double price) throws Exception;
}
