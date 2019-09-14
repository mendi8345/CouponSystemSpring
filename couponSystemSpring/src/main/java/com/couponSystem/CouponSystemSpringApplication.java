
package com.couponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({ "com.couponSystem" })

public class CouponSystemSpringApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringApplication.class, args);
		System.out.println("Go!");

		System.out.println("?!");

	}

}
