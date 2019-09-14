package com.couponSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.exeptions.CouponExistsException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.repository.CompanyRepository;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.service.CompanyService;

@Service
@Repository
public class CompanyDAO implements CompanyService, CouponClientFacade {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;
	private Company company;

	public CompanyDAO(Company company) {
		this.company = company;
	}

	@Override
	public void createCoupon(Coupon coupon) throws CouponExistsException {
		if (CouponAlreadyExists(coupon.getTitle()) != true) {
			this.couponRepository.save(coupon);
		} else {
			throw new CouponExistsException(
					" coupon with name " + coupon.getTitle() + " already exist, please try another name");

		}
	}

	@Override
	public void removeCoupon(long id) throws Exception {
		this.couponRepository.delete(id);
	}

	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		this.couponRepository.save(coupon);
	}

	@Override
	public Coupon getCoupon(long id) throws Exception {
		Coupon coupon = this.couponRepository.findOne(id);
		return coupon;
	}

	@Override
	public List<Coupon> getCompCoupons() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getCouponsByType(CouponType couponType) throws Exception {
		List<Coupon> coupons = getCompCoupons();
		for (Coupon c : coupons) {
			if (c.getCouponType() != couponType) {
				coupons.remove(c);
			}
		}
		return coupons;
	}

	public boolean CouponAlreadyExists(String title) {
		if (this.couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean loginCheck(String compName, String password) throws Exception {
		boolean loginStatus = false;
		try {
			if (this.companyRepository.findByCompNameAndPassword(compName, password) != null) {
				loginStatus = true;
			}
		} catch (Exception e) {
		}
		return loginStatus;
	}

	///
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}
