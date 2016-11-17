package com.okanmenevseoglu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SimpleProductManagerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimpleProductManagerApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SimpleProductManagerApplication.class, args);
	}
}
