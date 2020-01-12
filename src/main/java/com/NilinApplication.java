package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.nilin.model"})
@SpringBootApplication
public class NilinApplication {

    public static void main(String[] args) {
        SpringApplication.run(NilinApplication.class, args);
    }
}
