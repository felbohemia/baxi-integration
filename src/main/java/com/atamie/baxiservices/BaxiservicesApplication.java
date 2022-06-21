package com.atamie.baxiservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
//import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan({"com.atamie.baxiservices", "services","controllers"})
public class BaxiservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaxiservicesApplication.class, args);
	}
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}
}
