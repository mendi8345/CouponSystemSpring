package com.couponSystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByTitle(String title);

	boolean existsById(long id);

	Coupon findById(long id);

	List<Coupon> findByCouponType(CouponType couponType);

	public List<Coupon> findByEndDateBefore(Date date);

	// @Query("DELETE FROM CUSTOMER_COUPON WHERE COUPON_ID=?")
	// void deleteCustomerCoupon(long id);

	// @Query("DELETE FROM Company_Coupon WHERE COUPON_ID=?")
	// void deleteCompanyCoupon(long id);

}
