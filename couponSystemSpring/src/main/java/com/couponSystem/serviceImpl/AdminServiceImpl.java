package com.couponSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.exeptions.CompanyDoesNotExistsException;
import com.couponSystem.exeptions.CompanyExistsException;
import com.couponSystem.exeptions.CustomerDoesNotExistsException;
import com.couponSystem.exeptions.IncorrectEmailAddressException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.repository.CompanyRepository;
import com.couponSystem.repository.CustomerRepository;
import com.couponSystem.service.AdminService;

@Service
@Repository
public class AdminServiceImpl implements AdminService, CouponClientFacade {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void createCompany(Company company) throws Exception {

		if (CompanyNameAlreadyExists(company.getCompName()) != true) {
			if (company.getEmail() == null) {
				throw new IncorrectEmailAddressException(
						" IncorrectEmailAddress " + company.getEmail() + " , please try another email");
			}
			this.companyRepository.save(company);
		} else {
			throw new CompanyExistsException(
					" company with name " + company.getCompName() + " already exist, please try another name");
		}

	}

	@Override
	public void removeCompany(long id) throws Exception {
		Company company = this.companyRepository.findById(id);
		if (company == null) {
			throw new CompanyDoesNotExistsException(" company  does not exist, please try another name");
		}
		this.companyRepository.deleteById(id);
	}

	@Override
	public void updateCompany(long id, String email, String password) throws Exception {
		Company company = null;
		System.out.println("2222222");

		company = this.getCompany(id);
		company.setEmail(email);
		company.setPassword(password);
		this.companyRepository.save(company);
	}

	@Override
	public Company getCompany(long id) throws Exception {
		Company company = this.companyRepository.findById(id);
		if (company == null) {
			throw new CompanyDoesNotExistsException(" company  does not exist, please try another name");
		}
		return company;
	}

	@Override
	public List<Company> getAllCompany() throws Exception {
		return this.companyRepository.findAll();
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
		Customer customer = this.customerRepository.findById(id);
		if (customer == null) {
			throw new CustomerDoesNotExistsException(" customer does not exist, please try another name");
		}

		this.customerRepository.deleteById(id);

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

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean CompanyNameAlreadyExists(String compName) {
		if (this.companyRepository.findByCompName(compName) != null) {
			Company company = this.companyRepository.findByCompName(compName);
			System.out.println(" ompanyNameAlreadyExists 111111111" + company.toString());
			return true;
		}
		return false;
	}

	public boolean CustomerNameAlreadyExists(String custName) {
		if (this.customerRepository.findByCustName(custName) != null) {
			return true;
		}
		return false;
	}

}
