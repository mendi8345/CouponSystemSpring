package com.couponSystem.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.exeptions.InvalidTokenException;
import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.javabeans.Income;
import com.couponSystem.service.AdminService;
import com.couponSystem.service.IncomeService;
import com.couponSystem.utils.Tokens;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IncomeService incomeService;

	@Resource
	private Tokens tokens;

	public AdminService getAdminService(String token) {
		try {

			if (this.tokens.getTokens().containsKey(token)) {
				AdminService adminService = (AdminService) this.tokens.getTokens().get(token).getCouponClient();
				return adminService;
			} else {
				throw new InvalidTokenException("Invalid token: ", token);
			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable String token)
			throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		adminService.createCompany(company);
		ResponseEntity<String> result = new ResponseEntity<String>("company created  " + company.toString(),
				HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/deleteCompany/{id}/{token}")
	public ResponseEntity<String> removeCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}

		adminService.removeCompany(id);

		ResponseEntity<String> result = new ResponseEntity<String>("company with id " + id + " deleted Successfully ",
				HttpStatus.OK);
		return result;
	}

	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@PathVariable String token, @RequestParam long id,
			@RequestParam String email, @RequestParam String password) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}

		adminService.updateCompany(id, email, password);

		ResponseEntity<String> result = new ResponseEntity<String>(adminService.getCompany(id).toString(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCompany/{id}/{token}")
	public ResponseEntity<?> getCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		Company company = adminService.getCompany(id);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company, HttpStatus.OK);
		return result;

	}

	@GetMapping("/getAllCompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			// return new ResponseEntity<String>("Invalid token to Admin: " + token,
			// HttpStatus.UNAUTHORIZED);
		}

		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.getAllCompany(),
				HttpStatus.OK);
		return result;
	}

	@PostMapping("/createCustomer/{token}")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @PathVariable String token)
			throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		adminService.insertCustomer(customer);
		ResponseEntity<String> result = new ResponseEntity<String>("customer created " + customer, HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/deleteCustomer/{id}/{token}")
	public ResponseEntity<String> removeCustomer(@PathVariable long id, @PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		adminService.removeCustomer(id);
		ResponseEntity<String> result = new ResponseEntity<String>("customer with id " + id + " deleted Successfully ",
				HttpStatus.OK);
		return result;

	}

	@PostMapping("/updateCustomer/{token}/")
	public ResponseEntity<String> updateCustomer(@PathVariable String token, @RequestParam long id,
			@RequestParam String password) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		Customer customer = null;
		customer = adminService.getCustomer(id);
		customer.setPassword(password);
		adminService.updateCustomer(customer);

		ResponseEntity<String> result = new ResponseEntity<String>(customer.toString(), HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCustomer/{id}/{token}")
	public ResponseEntity<?> getCompamy(@PathVariable long id, @PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		Customer customer = adminService.getCustomer(id);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<?> getAllCompamy(@PathVariable String token) throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			System.out.println("111111111111111");
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}

		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(adminService.getAllCustomer(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/viewAllIncome/{token}")
	public ResponseEntity<?> viewAllIncome(@PathVariable String token) {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<List<Income>> result = new ResponseEntity<List<Income>>(this.incomeService.viewAllIncome(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/viewIncomeByCustomerId/{id}/{token}")
	public ResponseEntity<String> viewIncomeByCustomer(@PathVariable long id, @PathVariable String token) {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<String> result = new ResponseEntity<String>(
				"Total Customer Income = " + this.incomeService.viewIncomeByCustomer(id) + " shekels", HttpStatus.OK);
		return result;
	}

	@GetMapping("/viewIncomeByCompanyId/{id}/{token}")
	public ResponseEntity<String> viewIncomeByCompany(@PathVariable long id, @PathVariable String token)
			throws Exception {
		AdminService adminService = getAdminService(token);
		if (adminService == null) {
			return new ResponseEntity<String>("Invalid token to Admin: " + token, HttpStatus.UNAUTHORIZED);
		}

		ResponseEntity<String> result = new ResponseEntity<String>(
				"Total Company Income = " + this.incomeService.viewIncomeByCompany(id) + " shekels", HttpStatus.OK);
		return result;
	}
}
