package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Income;

public interface IncomeService {

	public void createIncome(Income income);

	public double viewIncomeByCompany(long id) throws Exception;

	public double viewIncomeByCustomer(long id);

	public List<Income> viewAllIncome();
}
