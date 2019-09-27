package com.couponSystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.DateUtils;
import com.couponSystem.exeptions.CouponExistsException;
import com.couponSystem.exeptions.CouponNotAvailableException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.javabeans.Income;
import com.couponSystem.javabeans.IncomeType;
import com.couponSystem.repository.CompanyRepository;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.repository.IncomeRepository;
import com.couponSystem.service.CompanyService;

@Service
@Repository
public class CompanyDAO implements CompanyService, CouponClientFacade {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IncomeRepository incomeRepository;

	private Company company;

	public Company getCompany() {
		return this.company;
	}

	@Override
	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyDAO() {
	}

	@Override
	public void createCoupon(Coupon coupon) throws Exception {

		if (CouponAlreadyExists(coupon.getTitle()) != true) {
			Company company = this.companyRepository.findById(this.company.getId());
			company.getCoupons().add(coupon);
			this.couponRepository.save(coupon);
			Income income = new Income();
			income.setId(this.company.getId());
			income.setAmount(100.0);
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setDate(DateUtils.GetCurrentDate());
			income.setName("Company " + company.getCompName());
			this.incomeRepository.save(income);
		} else {
			throw new CouponExistsException(
					" coupon with name " + coupon.getTitle() + " already exist, please tryanother name");
		}
	}

	@Override
	public void removeCoupon(long id) throws Exception {
		List<Coupon> coupons = getCompCoupons();

		Coupon coupon = this.couponRepository.findById(id);
		coupons.remove(coupon);
		this.company.setCoupons(coupons);

		this.couponRepository.delete(coupon);

	}

	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		this.couponRepository.save(coupon);
		Income income = new Income();
		income.setId(this.company.getId());
		income.setAmount(10.0);
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate(DateUtils.GetCurrentDate());
		income.setName("Company " + this.company.getCompName());
		this.incomeRepository.save(income);
	}

	@Override
	public Coupon getCoupon(long id) throws Exception {
		Coupon coupon = this.couponRepository.findOne(id);
		return coupon;
	}

	@Override
	public List<Coupon> getCompCoupons() throws Exception {
		Company company = this.companyRepository.getOne(this.company.getId());
		if (company != null) {
			List<Coupon> coupons = company.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponNotAvailableException("This company doesn't have any coupons");
			}
		} else {
			throw new Exception("This company doesn't exist");
		}
	}

	@Override
	public List<Coupon> getCouponsByType(CouponType couponType) throws Exception {
		List<Coupon> compCoupons = getCompCoupons();
		List<Coupon> coupons = new ArrayList<>();
		System.out.println(compCoupons.toString());
		for (Coupon c : compCoupons) {
			System.out.println("12345678910");
			if (c.getCouponType() == couponType) {

				coupons.add(c);
				System.out.println(c.toString());
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

	public Company findByCompNameAndPassword(String compName, String password) {
		return this.companyRepository.findByCompNameAndPassword(compName, password);
	}
}
