package com.example.demospringbootatomix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.demospringbootatomix.config")
public class DemoSpringBootAtomixApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootAtomixApplication.class, args);
	}

}
