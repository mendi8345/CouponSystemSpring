package com.couponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CouponRepository couponRepository;

	@PostMapping("/purchaseCoupon/{id}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable long id) throws Exception {

		try {
			Coupon coupon = this.couponRepository.findById(id);
			this.customerService.purchaseCoupon(coupon);
			return new ResponseEntity<>("Customer purchaed coupon :  " + coupon.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
		}
	}
}