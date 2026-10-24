package com.pulsefit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PulseFitApplication {
    public static void main(String[] args) {
        SpringApplication.run(PulseFitApplication.class, args);
        System.out.println("🚀 PulseFit Spring Boot Server running at http://localhost:8080");
    }
}