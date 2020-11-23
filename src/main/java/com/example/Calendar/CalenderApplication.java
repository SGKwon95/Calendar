package com.example.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalenderApplication.class, args);
	}

}
