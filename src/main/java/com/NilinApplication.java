package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class NilinApplication {

    public static void main(String[] args) {
        SpringApplication.run(NilinApplication.class, args);
    }

    @Bean
    Path path() {
        return Paths.get(System.getProperty("java.io.tmpdir"));
    }
}
