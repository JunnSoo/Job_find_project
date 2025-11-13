package com.project.it_job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ItJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItJobApplication.class, args);
	}

}
