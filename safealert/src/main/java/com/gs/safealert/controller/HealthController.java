package com.gs.safealert.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String hello() {
        return "API do SafeAlert";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}

