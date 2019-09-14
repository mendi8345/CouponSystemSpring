package com.couponSystem.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.CouponClientFacade;
import com.couponSystem.CouponSystem;
import com.couponSystem.exeptions.loginException;
import com.couponSystem.javabeans.ClientType;

@RestController
@RequestMapping("Login")
public class LoginController {

	@Autowired
	private Map<String, CouponClientFacade> tokens;

	@Autowired
	CouponSystem couponSystem;

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam String name, @RequestParam String password,
			@RequestParam String type) throws Exception {

		CouponClientFacade facade = null;
		String token = UUID.randomUUID().toString();
		// long lastAccessed=System.currentTimeMillis();

		try {
			facade = this.couponSystem.login(name, password, ClientType.valueOf(type));
			this.tokens.put(token, facade);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (loginException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}
// Session session = null;
// ClientType clientType = ClientType.valueOf(type);
// CouponClientFacade facade = this.couponSystem.login(name, password,
// clientType);
// HttpSession session = this.request.getSession(false);
// if (session != null) {
// session.invalidate(); // killing the session if exist
// session = this.request.getSession(true); // create a new session for a new
// client
//
// if (facade != null) {
// System.out.println("Facade debug - OK");
// session.setAttribute("facade", facade);
// Cookie cookie = new Cookie("Set-Cookie", "JSESSIONID=" +
// this.request.getSession().getId()
// + ";path=/RestCouponSystem/; HttpOnly; domain=/localhost; secure=false;");
// cookie.setComment(type);
// String goodResponse = new Gson().toJson(cookie);
//
// switch (clientType) {
// case admin:
// return Response.status(Response.Status.OK).entity(goodResponse).build();
//
// case company:
// return Response.status(Response.Status.OK).entity(goodResponse).build();
//
// case customer:
// return Response.status(Response.Status.OK).entity(goodResponse).build();
//
// }
// }
// String responseToJson = "Failed to auth, please try again. ";
// String badResponse = new Gson().toJson(responseToJson);
// return
// Response.status(Response.Status.UNAUTHORIZED).entity(badResponse).build();
// }
// }
