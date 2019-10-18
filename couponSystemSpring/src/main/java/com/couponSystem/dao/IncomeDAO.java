package com.couponSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.javabeans.Income;
import com.couponSystem.repository.IncomeRepository;
import com.couponSystem.service.IncomeService;

@Repository
@Service
public class IncomeDAO implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public void createIncome(Income income) {
		this.incomeRepository.save(income);

	}

	@Override
	public List<Income> viewAllIncome() {
		return this.incomeRepository.findAll();
	}

	@Override
	public Income getIncome(long id) {
		return this.incomeRepository.findOne(id);
	}
}
