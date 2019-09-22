package com.couponSystem.service;

import java.util.List;

import com.couponSystem.javabeans.Income;

public interface IncomeService {

	public void createIncome(Income income);

	public Income getIncome(long id);

	public List<Income> viewAllIncome();
}
