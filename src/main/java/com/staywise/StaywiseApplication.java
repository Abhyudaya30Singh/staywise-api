package com.staywise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StaywiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaywiseApplication.class, args);
	}

}
