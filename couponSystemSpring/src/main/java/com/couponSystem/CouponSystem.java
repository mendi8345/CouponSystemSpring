package com.couponSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.couponSystem.dao.AdminDAO;
import com.couponSystem.dao.CompanyDAO;
import com.couponSystem.dao.CustomerDAO;
import com.couponSystem.exeptions.loginException;
import com.couponSystem.javabeans.ClientType;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.service.CompanyService;
import com.couponSystem.service.CustomerService;

@Service
public class CouponSystem {
	@Autowired
	ApplicationContext contex;

	@Autowired
	AdminDAO adminDAO;

	@Autowired
	CompanyDAO companyDAO;
	@Autowired
	CustomerDAO customerDAO;

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
		case admin:
			if (name.equals("admin") && password.equals("1234")) {
				return this.adminDAO;
			}
		case company:
			loginStatus = this.companyDAO.loginCheck(name, password);
			if (loginStatus == true) {
				Company company = this.companyDAO.findByCompNameAndPassword(name, password);
				CompanyService companyService = this.contex.getBean(CompanyDAO.class);
				companyService.setCompany(company);
				return (CouponClientFacade) companyService;
			}

		case customer:
			loginStatus = this.customerDAO.loginCheck(name, password);
			if (loginStatus == true) {
				Customer customer = this.customerDAO.findByCustNameAndPassword(name, password);
				CustomerService customerService = this.contex.getBean(CustomerDAO.class);
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
