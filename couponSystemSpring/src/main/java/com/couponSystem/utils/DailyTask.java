package com.couponSystem.utils;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.couponSystem.DateUtils;
import com.couponSystem.repository.CouponRepository;

@Component
public class DailyTask {

	@Autowired
	private CouponRepository couponRepository;

	public void removeExpiredCoupons(Date date) throws Exception {

		System.out.println(this.couponRepository.findByEndDateBefore(date).toString());
		this.couponRepository.deleteAll(this.couponRepository.findByEndDateBefore(date));

	}

	// (fixedRate = 1000 * 60 * 60 * 24)
	@Scheduled(fixedRate = 1000)
	public void MyTask() throws Exception {
		System.out.println("public void MyTask() 00000000");
		removeExpiredCoupons(DateUtils.GetCurrentDate());
	}

}

// @Autowired
// private CompanyService companyService;
// @Autowired
// private CompanyRepository companyRepository;
// // @Autowired
// private CustomerRepository customerRepository;

// List<Customer> customers = this.customerRepository.findAll();
// List<Company> companies = this.companyRepository.findAll();
// for (Coupon coupon : this.couponRepository.findByEndDateBefore(date)) {
// System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
// coupon.toString());
// for (Company company : companies) {
// this.companyService.setCompany(company);
// List<Coupon> compCoupons = this.companyService.getCompCoupons();
// System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + compCoupons);
// if (compCoupons.contains(coupon)) {
// System.out.println(" test 222222222222222" +
// company.getCoupons().toString());
// this.companyService.removeCoupon(coupon.getId());
// // List<Coupon> compCoupons = company.getCoupons();
// // compCoupons.remove(coupon);
// // company.setCoupons(compCoupons);
// this.companyRepository.save(company);
// }
// }
// for (Customer customer : customers) {
// if (customer.getCoupons().contains(coupon)) {
// List<Coupon> custCoupons = customer.getCoupons();
// custCoupons.remove(coupon);
// customer.setCoupons(custCoupons);
//
// System.out.println("test 11111111111");
// this.customerRepository.save(customer);
// }
// }

// }

//
// import java.sql.Date;
// import java.util.List;
//
// import javax.annotation.Resource;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import com.couponSystem.DateUtils;
// import com.couponSystem.javabeans.Coupon;
// import com.couponSystem.repository.CouponRepository;
// import com.couponSystem.service.CompanyService;
//
// @Component
// public class DailyTask {
//
// @Autowired
// CouponRepository couponRepository;
// @Autowired
// CompanyService companyService;
//
// private boolean running = true;
//
// public void removeExpiredCoupons(Date date) throws Exception {
// System.out.println(this.couponRepository.findByEndDateBefore(date).toString());
// List<Coupon> expierdCoupons =this.couponRepository.findByEndDateBefore(date);
// this.couponRepository.deleteAll(expierdCoupons);
// // List<Coupon> coupons = this.couponRepository.findByEndDateBefore(date);
// // for (Coupon c : coupons) {
// // System.out.println("12345678910");
// //
// // this.couponRepository.delete(c.getId());
// // System.out.println(c.toString());
// // }
//
// }
//
// public void startThread() {
// new Thread(new Runnable() {
// @Override
// public void run() {
// while (DailyTask.this.running) {
// try {
// removeExpiredCoupons(DateUtils.GetCurrentDate());
// System.out.println("removeExpiredCoupons test ");
//
// } catch (Exception e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
// try {
// Thread.sleep(1000 * 60 * 60 * 24);
//
// // Thread.sleep(2000);
// } catch (InterruptedException e) {
// System.out.println("Eroor " + e.getMessage());
// }
// }
// }
// }).start();
// }
//
// public void stopThread() {
// this.running = false;
// }

// public List<Coupon> expiredCoupons() {
// List<Coupon> findAll =this.couponRepository.findAll();
// for (Coupon coupon : findAll) {
// this.couponRepository.findByEndDateBefore(coupon.getEndDate())
// }
//
// return null;
// }
// this.couponRepository.findByEndDateBefore(date)
//
// }