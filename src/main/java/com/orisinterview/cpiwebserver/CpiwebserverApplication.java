package com.orisinterview.cpiwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CpiwebserverApplication {

	public static void main(String[] args) {
		System.out.println("Starting webserver....");
		SpringApplication.run(CpiwebserverApplication.class, args);
	}

}
