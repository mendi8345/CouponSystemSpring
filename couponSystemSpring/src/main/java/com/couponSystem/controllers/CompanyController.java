package com.couponSystem.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	// Company company;////
	@Autowired
	private CompanyService companyService;

	// public CompanyController(Company company) {
	// this.company = company;
	// };

	@PostMapping("/createCoupon")
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) throws Exception {
		this.companyService.createCoupon(coupon);
		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/removeCoupon/{id}")
	public void deleteCoupon(@PathVariable long id) throws Exception {
		Coupon coupon = null;
		coupon = this.companyService.getCoupon(id);
		if (coupon != null) {
			this.companyService.removeCoupon(id);
		}
	}

	@PutMapping("/updateCoupon/{id}")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable long id, @RequestParam Date endDate,
			@RequestParam double price) throws Exception {
		Coupon coupon = null;
		coupon = this.companyService.getCoupon(id);
		if (coupon != null) {
			coupon.setEndDate(endDate);
			coupon.setPrice(price);
		}
		this.companyService.updateCoupon(coupon);
		ResponseEntity<Coupon> result = new ResponseEntity<>(coupon, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCoupon/{id}")
	public Coupon companyById(@PathVariable long id) throws Exception {
		return this.companyService.getCoupon(id);
	}

	@GetMapping("/getCompCoupons")
	public ResponseEntity<List<Coupon>> getCompCoupons() throws Exception {

		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(this.companyService.getCompCoupons(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCouponsByType/{type}")
	public ResponseEntity<List<Coupon>> getCouponsByType(@PathVariable String type) throws Exception {
		CouponType couponType = CouponType.valueOf(type);
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
				this.companyService.getCouponsByType(couponType), HttpStatus.OK);
		return result;
	}
}
