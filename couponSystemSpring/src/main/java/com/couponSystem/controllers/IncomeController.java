package com.couponSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.javabeans.Income;
import com.couponSystem.service.IncomeService;

@RestController
@RequestMapping("/Income")
public class IncomeController {

	@Autowired
	public IncomeService incomeService;

	@PostMapping("/createIncome")
	public ResponseEntity<Income> createIncome(@RequestBody Income income) {
		this.incomeService.createIncome(income);
		ResponseEntity<Income> result = new ResponseEntity<Income>(HttpStatus.OK);
		return result;
	}

	@GetMapping("/getAllIncome")
	public ResponseEntity<List<Income>> getAllIncome() {
		ResponseEntity<List<Income>> result = new ResponseEntity<List<Income>>(this.incomeService.getAllIncome(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getIncome/{id}")
	public ResponseEntity<Income> getIncome(@PathVariable long id) {
		ResponseEntity<Income> result = new ResponseEntity<Income>(this.incomeService.getIncome(id), HttpStatus.OK);
		return result;
	}

}
