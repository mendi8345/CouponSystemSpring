package com.couponSystem.javabeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
	private String message;

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
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, String message,
			CouponType couponType, double price, String image) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = couponType;
		this.message = message;
		this.price = price;
		this.image = image;
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
	public String getmessage() {
		return this.message;
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

	public void setmessage(String message) {
		this.message = message;
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
				+ this.endDate + ", amount=" + this.amount + ", message=" + this.message + ", couponType="
				+ this.couponType + ", price=" + this.price + ", image=" + this.image + "]";
	}
}
