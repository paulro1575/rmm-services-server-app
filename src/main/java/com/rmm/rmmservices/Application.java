package com.rmm.rmmservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class
 * @author Paul Rodríguez-Ch
 */
@SpringBootApplication
public class Application {

	public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LOGGER.info("RMM-Application started successfully!");
	}

}
