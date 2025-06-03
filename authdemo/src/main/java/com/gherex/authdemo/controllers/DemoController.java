package com.gherex.authdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("public/hello")
    public String publicHello() {
        return "Hola mundo público 🌎";
    }

    @GetMapping("user/hello")
    public String userHello() {
        return "Hola usuario 👤";
    }

    @GetMapping("admin/hello")
    public String adminHello() {
        return "Hola administrador 🔐";
    }
}
