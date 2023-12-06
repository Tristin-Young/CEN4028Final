package com.Young.CEN4802Final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cen4802FinalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Cen4802FinalApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Cen4802FinalApplication.class);
	}

}