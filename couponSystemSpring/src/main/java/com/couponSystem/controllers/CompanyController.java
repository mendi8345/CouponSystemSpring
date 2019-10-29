package com.couponSystem.controllers;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.exeptions.InvalidTokenException;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.service.CompanyService;
import com.couponSystem.utils.Tokens;

@RestController
@RequestMapping("/company")
public class CompanyController {

	// Company company;////

	@Resource
	private Tokens tokens;

	public CompanyService getCompanyService(String token) {
		try {

			if (this.tokens.getTokens().containsKey(token)) {
				CompanyService companyService = (CompanyService) this.tokens.getTokens().get(token).getCouponClient();
				return companyService;
			} else {
				throw new InvalidTokenException("Invalid token: ", token);
			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PostMapping("/createCoupon/{token}")
	public ResponseEntity<String> createCoupon(@PathVariable String token, @RequestBody Coupon coupon)
			throws Exception {
		System.out.println("1111111111111111111");
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		System.out.println("222222222222222222");
		companyService.createCoupon(coupon);
		System.out.println("33333333333333333");
		ResponseEntity<String> result = new ResponseEntity<String>(coupon.toString(), HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/removeCoupon/{id}/{token}")
	public ResponseEntity<String> deleteCoupon(@PathVariable long id, @PathVariable String token) throws Exception {
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		Coupon coupon = null;
		coupon = companyService.getCoupon(id);
		companyService.removeCoupon(id);
		return new ResponseEntity<String>("coupon deleted  " + coupon, HttpStatus.OK);
	}

	@PostMapping("/updateCoupon/{token}")
	public ResponseEntity<String> updateCoupon(@PathVariable String token, @RequestParam long id,
			@RequestParam Date endDate, @RequestParam double price) throws Exception {
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}

		Coupon coupon = null;
		coupon = companyService.getCoupon(id);
		if (coupon != null) {
			coupon.setEndDate(endDate);
			coupon.setPrice(price);
			companyService.updateCoupon(coupon);
			ResponseEntity<String> result = new ResponseEntity<>(coupon.toString(), HttpStatus.OK);
			return result;
		}
		return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/getCoupon/{id}/{token}")
	public ResponseEntity<?> getCouponById(@PathVariable long id, @PathVariable String token) throws Exception {
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			ResponseEntity<String> result = new ResponseEntity<String>(
					"This company does not have a coupon with this ID", HttpStatus.UNAUTHORIZED);
			return result;
		}
		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(companyService.getCoupon(id), HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCompCoupons/{token}")
	public ResponseEntity<List<Coupon>> getCompCoupons(@PathVariable String token) throws Exception {
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			// return new ResponseEntity<>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}

		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(companyService.getCompCoupons(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCouponsByType/{couponType}/{token}")
	public ResponseEntity<List<Coupon>> getCouponsByType(@PathVariable CouponType couponType,
			@PathVariable String token) throws Exception {
		// CouponType couponType = CouponType.valueOf(couponType);
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			// return new ResponseEntity<>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
				companyService.getCouponsByType(couponType), HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCouponsByPrice/{price}/{token}")
	public ResponseEntity<List<Coupon>> getCouponsByPrice(@PathVariable double price, @PathVariable String token)
			throws Exception {
		// CouponType couponType = CouponType.valueOf(couponType);
		CompanyService companyService = getCompanyService(token);
		if (companyService == null) {
			// return new ResponseEntity<>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(companyService.getCouponsByPrice(price),
				HttpStatus.OK);
		return result;
	}
}
