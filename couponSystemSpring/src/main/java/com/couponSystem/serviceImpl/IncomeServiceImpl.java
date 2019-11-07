package com.couponSystem.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.couponSystem.javabeans.Income;
import com.couponSystem.javabeans.IncomeType;
import com.couponSystem.repository.IncomeRepository;
import com.couponSystem.service.IncomeService;

@Repository
@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public synchronized void createIncome(Income income) {
		this.incomeRepository.save(income);

	}

	@Override
	public List<Income> viewAllIncome() {
		return this.incomeRepository.findAll();
	}

	@Override
	public double viewIncomeByCompany(long id) throws Exception {
		double totalCompanyIncome = 0;

		List<Income> incomes = viewAllIncome();
		List<Income> companyIncomes = new ArrayList<>();
		System.out.println(incomes.toString());
		for (Income i : incomes) {

			System.out.println("12345678910");
			if (i.getClientId() == id) {
				if ((i.getDescription() == IncomeType.COMPANY_NEW_COUPON)
						|| (i.getDescription() == IncomeType.COMPANY_UPDATE_COUPON)) {

					companyIncomes.add(i);
					totalCompanyIncome = totalCompanyIncome + i.getAmount();
					System.out.println(i.toString());
				} else {
					System.out.println("There is no revenue for this company");
				}
			}
		}
		return totalCompanyIncome;
	}

	@Override
	public double viewIncomeByCustomer(long id) {
		double totalCustomerIncome = 0;

		List<Income> incomes = viewAllIncome();
		List<Income> companyIncomes = new ArrayList<>();
		System.out.println(incomes.toString());
		for (Income i : incomes) {

			System.out.println("12345678910");
			if (i.getClientId() == id) {
				if ((i.getDescription() == IncomeType.CUSTOMER_PURCHASE)) {

					companyIncomes.add(i);
					totalCustomerIncome = totalCustomerIncome + i.getAmount();
					System.out.println(i.toString());
				} else {
					System.out.println("There is no revenue for this customer");
				}
			}
		}
		return totalCustomerIncome;
	}
}