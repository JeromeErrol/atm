package com.jerome.atm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

	@Value("${twenties}")
	private int twenties;
	@Value("${fifties}")
	private int fifties;

	@Bean
	public Atm atm(){
		return new Atm(twenties, fifties);
	}
}
