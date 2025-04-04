package com.example.demo;

import com.example.demo.services.OperatorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner init(OperatorService operatorService) {
		return args -> {
			operatorService.importCSV("data/operadoras_ativas.csv");
		};
	}

}

