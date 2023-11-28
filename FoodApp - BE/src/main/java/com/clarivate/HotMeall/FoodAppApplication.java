package com.clarivate.HotMeall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.clarivate")
@EnableJpaRepositories(basePackages = "com.clarivate")
@EntityScan(basePackages = "com.clarivate")
public class FoodAppApplication {

	public static void main(String[] args) {
		//Clarivate Batch 3 (September Batch)
		//Group 6
		SpringApplication.run(FoodAppApplication.class, args);
	}

}
