package com.couponSystem.javabeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CouponTable")
public class Coupon {
	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private String messege;

	private CouponType couponType;
	private double price;
	private String image;

	/**
	 * empty CTOR
	 */
	public Coupon() {

	}

	/**
	 * Full CTOR
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, String messege,
			CouponType couponType, double price, String image) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = couponType;
		this.messege = messege;
		this.price = price;
		this.image = image;
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
	public String getTitle() {
		return this.title;
	}

	@Column(nullable = false)
	public Date getStartDate() {
		return this.startDate;
	}

	@Column(nullable = false)
	public Date getEndDate() {
		return this.endDate;
	}

	@Column(nullable = false)
	public int getAmount() {
		return this.amount;
	}

	@Column(nullable = false)
	public String getMessege() {
		return this.messege;
	}

	@Column(nullable = false)
	public double getPrice() {
		return this.price;
	}

	@Column(nullable = false)
	public String getImage() {
		return this.image;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(20)")

	public CouponType getCouponType() {
		return this.couponType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + this.id + ", title=" + this.title + ", startDate=" + this.startDate + ", endDate="
				+ this.endDate + ", amount=" + this.amount + ", messege=" + this.messege + ", couponType="
				+ this.couponType + ", price=" + this.price + ", image=" + this.image + "]";
	}
}
