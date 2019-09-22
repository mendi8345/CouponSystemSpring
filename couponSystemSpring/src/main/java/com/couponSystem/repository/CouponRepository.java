package com.couponSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByTitle(String title);

	boolean existsById(long id);

	Coupon findById(long id);

	List<Coupon> findByCouponType(CouponType couponType);

}
