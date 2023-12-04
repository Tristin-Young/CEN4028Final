package com.Young.CEN4802Final.Repo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public interface WellnessApp {

    public static void main(String[] args) {
        SpringApplication.run(WellnessApp.class, args);
    }
}
