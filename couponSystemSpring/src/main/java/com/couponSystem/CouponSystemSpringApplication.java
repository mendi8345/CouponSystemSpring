
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
	// @Autowired
	// DailyTask dailyTask;
	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringApplication.class, args);
		System.out.println("Go!");
		// DailyTask dailyTask = context.getBean(DailyTask.class);
		// System.out.println(DateUtils.GetCurrentDate());
		// dailyTask.MyTask();
		System.out.println("?!");

	}

}
