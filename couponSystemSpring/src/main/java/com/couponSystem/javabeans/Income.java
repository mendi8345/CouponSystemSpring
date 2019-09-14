package com.couponSystem.javabeans;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "income")

public class Income {

	private long id;

	private String name;

	@Basic(optional = false)
	@Column(nullable = false, name = "date")
	private Date date;

	@Basic(optional = false)
	@Column(nullable = false, name = "description")
	@Enumerated(EnumType.STRING)
	private IncomeType description;

	@Basic(optional = false)
	@Column(nullable = false, name = "amount")
	private double amount;

	public Income(long id, String name, Date date, IncomeType description, double amount) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.amount = amount;
	}

	public Income() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	@Basic(optional = false)
	@Column(nullable = false)
	public String getName() {
		return this.name;
	}

	@Basic(optional = false)
	@Column(nullable = false)
	public Date getDate() {
		return this.date;
	}

	@Basic(optional = false)
	@Column(nullable = false)
	public IncomeType getDescription() {
		return this.description;
	}

	@Basic(optional = false)
	@Column(nullable = false)
	public double getAmount() {
		return this.amount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(IncomeType description) {
		this.description = description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
