package com.creditsuisse.logprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.creditsuisse.logprocessor"})
public class LogProcessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogProcessorApplication.class, args);
	}

}
