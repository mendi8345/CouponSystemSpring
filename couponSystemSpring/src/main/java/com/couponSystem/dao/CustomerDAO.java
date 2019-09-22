package com.couponSystem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() throws Exception {
		Customer customer = this.customerRepository.findById(this.customer.getId());
		return customer;
	}

	@Transactional
	@Override
	public void purchaseCoupon(Coupon coupon) throws Exception {
		try {
			if (!this.couponRepository.existsById(coupon.getId())) {
				throw new CouponNotAvailableException("This coupon doesn't exist, please try another one !");
			}

			Coupon LocalCoupon = this.couponRepository.findById(coupon.getId());
			System.out.println("55555555555555555555555555" + LocalCoupon.toString());

			List<Coupon> coupons = new ArrayList<>();
			System.out.println("444444444444444" + LocalCoupon.toString());

			coupons.add(LocalCoupon);
			System.out.println("333333333333333333333333" + this.customer.getCoupons());
			Customer customer = this.customer;
			customer.setCoupons(coupons);
			System.out.println("22222222222222222222" + LocalCoupon.toString());
			this.customerRepository.save(this.customer);
			this.couponRepository.save(coupon);

			if (coupon.getAmount() <= 0) {
				throw new CouponNotAvailableException("Unable to purache coupon with 0 amount");
			}
			if (coupon.getEndDate().getTime() <= DateUtils.GetCurrentDate().getTime()) {
				throw new CouponNotAvailableException("This coupon is out of stock");
			}
			Income income = new Income();
			income.setId(this.customer.getId());
			income.setAmount(coupon.getPrice());
			income.setDate(DateUtils.GetCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			income.setName("customer " + this.customer.getCustName());
			this.incomeService.createIncome(income);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Coupon> getAllPurchasedCoupon() throws Exception {
		return null;
	}

	@Override
	public List<Coupon> getAllPurchasedCouponByType(CouponType couponType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllPurchasedCouponByPrice(double price) throws Exception {
		// TODO Auto-generated method stub
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
		this.customerRepository.findByCustNameAndPassword(name, password);
		return null;
	}
}
