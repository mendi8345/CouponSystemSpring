package com.couponSystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.DateUtils;
import com.couponSystem.exeptions.CouponNotAvailableException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.javabeans.Income;
import com.couponSystem.javabeans.IncomeType;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.repository.CustomerRepository;
import com.couponSystem.service.CustomerService;
import com.couponSystem.service.IncomeService;

@Service
@Repository
public class CustomerDAO implements CustomerService, CouponClientFacade {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private IncomeService incomeService;

	private Customer customer;

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Customer getCustomer() throws Exception {
		Customer customer = this.customerRepository.findById(this.customer.getId());
		return customer;
	}

	@Override
	public void purchaseCoupon(Coupon coupon) throws Exception {
		try {
			if (!this.couponRepository.existsById(coupon.getId())) {
				throw new CouponNotAvailableException("This coupon doesn't exist, please try another one !");
			}
			//
			if (coupon.getAmount() <= 0) {
				throw new CouponNotAvailableException("Unable to purache coupon with amount");
			}
			// if (coupon.getEndDate().getTime() <= DateUtils.GetCurrentDate().getTime()) {
			// throw new CouponNotAvailableException("This coupon is out of stock");
			// }
			System.out.println("11111111111111111");

			this.customer = this.customerRepository.findById(this.customer.getId());
			List<Coupon> coupons = getAllPurchasedCoupon();
			coupons.add(coupon);
			this.customer.setCoupons(coupons);
			this.customerRepository.save(this.customer);
			System.out.println("22222222222222");

			Income income = new Income();
			income.setAmount(coupon.getPrice());
			income.setDate(DateUtils.GetCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			System.out.println("22222222222222");

			income.setName("customer " + this.customer.getCustName());
			this.incomeService.createIncome(income);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Coupon> getAllPurchasedCoupon() throws Exception {

		return this.customer.getCoupons();
	}

	@Override
	public List<Coupon> getAllPurchasedCouponByType(CouponType couponType) throws Exception {
		List<Coupon> custCoupons = getAllPurchasedCoupon();
		List<Coupon> coupons = new ArrayList<>();
		System.out.println(custCoupons.toString());
		for (Coupon c : custCoupons) {
			System.out.println("12345678910");
			if (c.getCouponType() == couponType) {

				coupons.add(c);
				System.out.println(c.toString());
			}
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllPurchasedCouponByPrice(double price) throws Exception {
		try {

			List<Coupon> custCoupons = getAllPurchasedCoupon();
			List<Coupon> coupons = new ArrayList<>();
			System.out.println(custCoupons.toString());
			for (Coupon c : custCoupons) {
				System.out.println("12345678910");
				if (c.getPrice() <= price) {

					coupons.add(c);
					System.out.println(c.toString());
				}
			}
			return coupons;

		} catch (Exception e) {
			System.out.println("Failed to get all coupons by price " + e.getMessage());
		}
		return null;

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean loginCheck(String name, String password) {
		boolean loginStatus = false;
		try {
			if (this.customerRepository.findByCustNameAndPassword(name, password) != null) {
				loginStatus = true;
			}
		} catch (Exception e) {
		}
		return loginStatus;
	}

	public Customer findByCustNameAndPassword(String name, String password) {

		return this.customerRepository.findByCustNameAndPassword(name, password);

	}
}
