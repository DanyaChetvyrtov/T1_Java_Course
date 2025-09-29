package ru.ex.creditms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CreditMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditMsApplication.class, args);
	}

}
