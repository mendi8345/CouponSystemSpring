package com.couponSystem.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.exeptions.InvalidTokenException;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.service.CustomerService;
import com.couponSystem.utils.Tokens;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Resource
	private Tokens tokens;

	public CustomerService getCustomerService(String token) {
		try {

			if (this.tokens.getTokens().containsKey(token)) {
				CustomerService customerService = (CustomerService) this.tokens.getTokens().get(token)
						.getCouponClient();
				return customerService;
			} else {
				throw new InvalidTokenException("Invalid token: ", token);
			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PostMapping("/purchaseCoupon/{id}/{token}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable long id, @PathVariable String token) throws Exception {

		try {
			CustomerService customerService = getCustomerService(token);
			if (customerService == null) {
				return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
			}
			customerService.purchaseCoupon(id);
			return new ResponseEntity<String>("Customer purchaed coupon :  " + customerService.getCustomer().toString(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getAllPurchasedCoupon/{token}")
	public ResponseEntity<?> getAllCustomerCoupons(@PathVariable String token) throws Exception {
		CustomerService customerService = getCustomerService(token);
		if (customerService == null) {
			return new ResponseEntity<String>("Invalid token to Customer: " + token, HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(customerService.getAllPurchasedCoupon(),
				HttpStatus.OK);
		return result;

	}

	@GetMapping("/getAllPurchasedCouponByType/{couponType}/{token}")
	public ResponseEntity<List<Coupon>> getAllPurchasedCouponByType(@PathVariable CouponType couponType,
			@PathVariable String token) throws Exception {
		CustomerService customerService = getCustomerService(token);
		if (customerService == null) {
			// return new ResponseEntity<>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
				customerService.getAllPurchasedCouponByType(couponType), HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCustomerByPrice/{price}/{token}")
	public ResponseEntity<List<Coupon>> getCustomerByPrice(@PathVariable double price, @PathVariable String token)
			throws Exception {

		CustomerService customerService = getCustomerService(token);
		if (customerService == null) {
			// return new ResponseEntity<>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}
		try {
			ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
					customerService.getAllPurchasedCouponByPrice(price), HttpStatus.OK);
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}