package com.example.reminderapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReminderapplicationApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReminderapplicationApplication.class, args);
	}
}
