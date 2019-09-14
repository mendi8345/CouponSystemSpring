package com.couponSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponSystem.javabeans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByTitle(String title);

	// Coupon findByType(CouponType couponType);

}
