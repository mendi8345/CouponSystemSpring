package com.couponSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.exeptions.CompanyExistsException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.repository.CompanyRepository;
import com.couponSystem.repository.CustomerRepository;
import com.couponSystem.service.AdminService;

@Service
@Repository
public class AdminDAO implements AdminService, CouponClientFacade {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void createCompany(Company company) throws CompanyExistsException {

		if (CompanyNameAlreadyExists(company.getCompName()) != true) {
			this.companyRepository.save(company);
		} else {
			throw new CompanyExistsException(
					" company with name " + company.getCompName() + " already exist, please try another name");
		}

	}

	@Override
	public void removeCompany(long id) throws Exception {
		this.companyRepository.delete(id);
	}

	@Override
	public void updateCompany(Company company) throws Exception {
		this.companyRepository.save(company);
	}

	@Override
	public Company getCompany(long id) throws Exception {
		return this.companyRepository.findById(id);
	}

	@Override
	public List<Company> getAllCompany() throws Exception {

		return this.companyRepository.findAll();
	}

	public boolean CompanyNameAlreadyExists(String compName) {
		if (this.companyRepository.findByCompName(compName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void insertCustomer(Customer customer) throws Exception {
		if (CustomerNameAlreadyExists(customer.getCustName()) != true) {
			this.customerRepository.save(customer);
		} else {
			throw new CompanyExistsException(
					" customer with name " + customer.getCustName() + " already exist, please try another name");
		}
	}

	@Override
	public void removeCustomer(long id) throws Exception {
		this.customerRepository.delete(id);

	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(long id) throws Exception {
		return this.customerRepository.findById(id);
	}

	@Override
	public List<Customer> getAllCustomer() throws Exception {
		return this.customerRepository.findAll();
	}

	public boolean CustomerNameAlreadyExists(String custName) {
		if (this.customerRepository.findByCustName(custName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

}
