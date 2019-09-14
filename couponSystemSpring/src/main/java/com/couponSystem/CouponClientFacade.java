package com.couponSystem;

import org.springframework.stereotype.Repository;

import com.couponSystem.javabeans.ClientType;

@Repository
public interface CouponClientFacade {

	public CouponClientFacade login(String name, String password, ClientType clientType);

}
