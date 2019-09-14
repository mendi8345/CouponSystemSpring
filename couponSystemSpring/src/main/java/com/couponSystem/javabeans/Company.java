package com.couponSystem.javabeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CompanyTable")
public class Company {

	/**
	 * Data Members
	 */

	private long id;

	private String compName;
	private String password;
	private String email;
	private List<Coupon> coupons;

	/**
	 * Full CTOR
	 */
	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	/**
	 * empty CTOR
	 */
	public Company() {
	}

	/**
	 * getters & setters
	 */
	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	@Column(nullable = false)
	public String getCompName() {
		return this.compName;
	}

	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}

	@Column(nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company [id=" + this.id + ", compName=" + this.compName + ", password=" + this.password + ", email="
				+ this.email + "]";
	}

	@OneToMany
	public List<Coupon> getCoupons() {
		return this.coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

}
