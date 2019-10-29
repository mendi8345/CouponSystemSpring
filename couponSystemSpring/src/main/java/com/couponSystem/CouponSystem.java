package com.couponSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.couponSystem.exeptions.loginException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.service.CompanyService;
import com.couponSystem.service.CustomerService;
import com.couponSystem.serviceImpl.AdminServiceImpl;
import com.couponSystem.serviceImpl.CompanyServiceImpl;
import com.couponSystem.serviceImpl.CustomerServiceImpl;

@Service
public class CouponSystem {
	@Autowired
	ApplicationContext contex;

	@Autowired
	AdminServiceImpl adminDAO;

	@Autowired
	CompanyServiceImpl companyDAO;
	@Autowired
	CustomerServiceImpl customerDAO;

	// @Autowired
	// CustomerS companyService;

	// private DailyTask dailyTask = new DailyTask();
	private static CouponSystem instance = new CouponSystem();

	//
	public static CouponSystem getInstance() {
		return instance;
	}

	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		boolean loginStatus;
		switch (clientType) {
		case ADMIN:
			if (name.equals("admin") && password.equals("1234")) {
				return this.adminDAO;
			}
		case COMPANY:
			loginStatus = this.companyDAO.loginCheck(name, password);
			if (loginStatus == true) {
				Company company = this.companyDAO.findByCompNameAndPassword(name, password);
				CompanyService companyService = this.contex.getBean(CompanyServiceImpl.class);
				companyService.setCompany(company);
				return (CouponClientFacade) companyService;
			}

		case CUSTOMER:
			loginStatus = this.customerDAO.loginCheck(name, password);
			if (loginStatus == true) {
				Customer customer = this.customerDAO.findByCustNameAndPassword(name, password);
				CustomerService customerService = this.contex.getBean(CustomerServiceImpl.class);
				customerService.setCustomer(customer);
				System.out.println(customer.toString());
				return (CouponClientFacade) customerService;
			}
		}
		throw new loginException("Wrong name or password, please try again!");
	}

}
// System.out.println("companyDBDAO.login(name, password)" +
// companyDBDAO.login(name, password));
//
// if (loginStatus) {
// Company company = new Company();
// company.setId(companyDBDAO.GetCompIdByName(name));
// company.setCompName(name);
// company.setPassword(password);
// CompanyFacade companyFacade = new CompanyFacade(company);
// // companyFacade.setCompanyDAO(this.companyDAO);
// // companyFacade.setCouponDAO(this.couponDAO);
//
// return companyFacade;
//
// }
// }
//
// if (clientType == ClientType.customer) {
//
// CustomerDBDAO customerDBDAO = new CustomerDBDAO();
// boolean loginStatus = customerDBDAO.login(name, password);
// System.out.println("customerDBDAO.login(name, password)" +
// customerDBDAO.login(name, password));
// if (loginStatus) {
//
// Customer customer = new Customer();
// customer.setId(customerDBDAO.GetCustIdByName(name));
// customer.setCustName(name);
// customer.setPassword(password);
// CustomerFacade customerFacade = new CustomerFacade(customer);
//
// return customerFacade;
//
// }
// }
//
// return null;
// }
//
