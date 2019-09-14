package com.couponSystem.javabeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerTable")

public class Customer {

	private long id;
	private String custName;
	private String password;
	private List<Coupon> coupons;

	/**
	 * Full CTOR
	 */
	public Customer(long id, String custName, String password) {
		this.id = id;
		this.custName = custName;
		this.password = password;
	}

	/**
	 * Empty CTOR
	 */
	public Customer() {
	}

	/**
	 * getters & setters
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	@Column(nullable = false)
	public String getCustName() {
		return this.custName;
	}

	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [id=" + this.id + ", custName=" + this.custName + ", password=" + this.password + "]";
	}

	@OneToMany
	public List<Coupon> getCoupons() {
		return this.coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

}
