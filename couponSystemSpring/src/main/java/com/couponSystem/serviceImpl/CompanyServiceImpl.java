package com.couponSystem.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.DateUtils;
import com.couponSystem.exeptions.CouponDoesNotExistsException;
import com.couponSystem.exeptions.CouponExistsException;
import com.couponSystem.exeptions.CouponNotAvailableException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Coupon;
import com.couponSystem.javabeans.CouponType;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.javabeans.Income;
import com.couponSystem.javabeans.IncomeType;
import com.couponSystem.repository.CompanyRepository;
import com.couponSystem.repository.CouponRepository;
import com.couponSystem.repository.CustomerRepository;
import com.couponSystem.service.CompanyService;
import com.couponSystem.service.IncomeService;

@Service
@Repository
public class CompanyServiceImpl implements CompanyService, CouponClientFacade {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private IncomeService incomeService;

	private Company company;

	@Override
	public Company getCompany() {
		return this.company;
	}

	@Override
	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyServiceImpl() {
	}

	@Override
	public synchronized void createCoupon(Coupon coupon) throws Exception {

		if (!CouponAlreadyExists(coupon.getTitle())) {
			Company company = this.companyRepository.findById(this.company.getId());
			this.couponRepository.save(coupon);
			company.getCoupons().add(coupon);
			this.companyRepository.save(company);
			Income income = new Income();
			income.setAmount(100.0);
			income.setClientId(company.getId());
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setDate(DateUtils.GetCurrentDate());
			income.setName("Company " + company.getCompName());
			this.incomeService.createIncome(income);
		} else {
			throw new CouponExistsException(
					" coupon with name " + coupon.getTitle() + " already exist, please tryanother name");
		}
	}

	@Override
	public void removeCoupon(long id) throws Exception {
		List<Coupon> coupons = getCompCoupons();
		System.out.println("in removeCoupon metod ");
		Coupon coupon = this.couponRepository.findById(id);
		if (coupon == null) {
			throw new CouponDoesNotExistsException(" coupon does not exist, please try another Coupon");
		}
		coupons.remove(coupon);
		this.company.setCoupons(coupons);
		List<Customer> customers = this.customerRepository.findAll();
		for (Customer c : customers) {
			System.out.println("12345678910");
			if (c.getCoupons().contains(coupon)) {
				List<Coupon> custCoupons = c.getCoupons();
				custCoupons.remove(coupon);
				c.setCoupons(custCoupons);
			}
		}
		this.couponRepository.delete(coupon);

	}

	@Override
	public synchronized void updateCoupon(Coupon coupon) throws Exception {
		if (coupon == null) {
			throw new CouponDoesNotExistsException(" coupon does not exist, please try another Coupon");
		}
		this.couponRepository.save(coupon);
		System.out.println("111111111111111");
		Income income = new Income();
		income.setAmount(10.0);
		income.setClientId(this.company.getId());
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate(DateUtils.GetCurrentDate());
		income.setName("Company " + this.company.getCompName());
		this.incomeService.createIncome(income);
	}

	@Override
	public Coupon getCoupon(long id) throws Exception {

		Company company = this.companyRepository.getOne(this.company.getId());
		if (company.getCoupons().contains(this.couponRepository.findById(id))) {
			Coupon coupon = this.couponRepository.findById(id);
			if (coupon == null) {
				throw new CouponDoesNotExistsException(" coupon does not exist, please try another Coupon");
			}
			return coupon;
		} else {
			throw new Exception("This company doesn't have such coupon");
		}
		// TODO: handle exception
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
		List<Coupon> coupons = null;
		System.out.println(compCoupons.toString());
		for (Coupon c : compCoupons) {
			System.out.println("12345678910");
			if (c.getCouponType() == couponType) {
				coupons = new ArrayList<>();
				coupons.add(c);
				System.out.println(c.toString());
			}
			if (coupons != null) {
				throw new CouponNotAvailableException("This company doesn't have coupons of this type");
			}
		}
		return coupons;
	}

	@Override
	public List<Coupon> getCouponsByPrice(double price) throws Exception {
		List<Coupon> compCoupons = getCompCoupons();
		System.out.println(compCoupons.toString());
		List<Coupon> coupons = null;
		for (Coupon c : compCoupons) {
			System.out.println("12345678910");
			if (c.getPrice() <= price) {
				coupons = new ArrayList<>();
				coupons.add(c);
				System.out.println(c.toString());
			}
		}
		if (coupons == null) {
			throw new CouponNotAvailableException("This company doesn't have coupons of this price");
		}
		return coupons;
	}

	public boolean CouponAlreadyExists(String title) {
		if (this.couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}

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
