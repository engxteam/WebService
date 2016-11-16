package com.epam.microservices.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author kyadav
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages="com.epam.microservices.config") // Disable component scanner
public class WebServer {



	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * @param args Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebServer.class, args);
	}

}
