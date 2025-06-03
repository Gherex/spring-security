package com.gherex.authwithdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthWithDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthWithDbApplication.class, args);
    }
}
