package com.gherex.authwithjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hola mundo público 🌎";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hola usuario 👤";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hola administrador 🔐";
    }
}