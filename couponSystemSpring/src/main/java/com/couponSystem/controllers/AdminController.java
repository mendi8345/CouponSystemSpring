package com.couponSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.javabeans.Company;
import com.couponSystem.javabeans.Customer;
import com.couponSystem.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/createCompany")
	public ResponseEntity<Company> createCompany(@RequestBody Company company) throws Exception {
		this.adminService.createCompany(company);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company, HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/removeCompany/{id}")
	public ResponseEntity removeCompany(@PathVariable long id) throws Exception {
		Company company = null;
		company = this.adminService.getCompany(id);
		if (company != null) {
			this.adminService.removeCompany(id);
		}
		ResponseEntity<Company> result = new ResponseEntity<Company>(HttpStatus.OK);
		return result;
	}

	@PutMapping("/updateCompany/{id}")
	public ResponseEntity updateCompany(@PathVariable long id, @RequestParam String email,
			@RequestParam String password) throws Exception {
		Company company = null;
		company = this.adminService.getCompany(id);
		company.setEmail(email);
		company.setPassword(password);
		this.adminService.updateCompany(company);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCompamy/{id}")
	public ResponseEntity getCompany(@PathVariable long id) throws Exception {
		Company company = this.adminService.getCompany(id);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<List<Company>> getAllCompanies() throws Exception {

		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(this.adminService.getAllCompany(),
				HttpStatus.OK);
		return result;
	}

	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception {
		this.adminService.insertCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/removeCustomer/{id}")
	public ResponseEntity removeCustomer(@PathVariable long id) throws Exception {
		Customer customer = null;
		customer = this.adminService.getCustomer(id);
		if (customer != null) {
			this.adminService.removeCustomer(id);
		}
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(HttpStatus.OK);
		return result;
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity updateCustomer(@PathVariable long id, @RequestParam String password) throws Exception {
		Customer customer = null;
		customer = this.adminService.getCustomer(id);
		customer.setPassword(password);
		this.adminService.updateCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCustomer/{id}")
	public ResponseEntity getCompamy(@PathVariable long id) throws Exception {
		Customer customer = this.adminService.getCustomer(id);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		return result;
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCompamy() throws Exception {

		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(this.adminService.getAllCustomer(),
				HttpStatus.OK);
		return result;
	}
}
