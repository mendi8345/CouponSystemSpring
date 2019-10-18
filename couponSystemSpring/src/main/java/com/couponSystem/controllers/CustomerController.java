package com.couponSystem.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private Map<String, CouponClientFacade> tokens;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CouponRepository couponRepository;

	Customer customer;

	// private C exists(String token) {
	// return LoginController.tokens.get(token);
	// }

	public CustomerService getCustomerService() {
		return this.customerService;
	}

	@PostMapping("/purchaseCoupon/{id}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable long id) throws Exception {

		try {
			Coupon coupon = this.couponRepository.findById(id);
			this.customer = getCustomerService().getCustomer();
			System.out.println(this.customer.toString() + "kljhhhhhhhhhhhhhhhhhh");
			this.customerService.purchaseCoupon(coupon);

			return new ResponseEntity<>("Customer purchaed coupon :  " + this.customerService.getCustomer().toString(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getAllPurchasedCoupon/")
	public ResponseEntity<List<Coupon>> getAllCustomerCoupons() throws Exception {
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
				this.customerService.getAllPurchasedCoupon(), HttpStatus.OK);
		return result;

	}

	@GetMapping("/getAllPurchasedCouponByType/{couponType}/")
	public ResponseEntity<List<Coupon>> getAllPurchasedCouponByType(@PathVariable CouponType couponType)
			throws Exception {
		// CouponType couponType = CouponType.valueOf(couponType);
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(
				this.customerService.getAllPurchasedCouponByType(couponType), HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCustomerByPrice/{price}")
	public List<Coupon> getCustomerByPrice(@PathVariable double price) throws Exception {

		try {
			return this.customerService.getAllPurchasedCouponByPrice(price);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}