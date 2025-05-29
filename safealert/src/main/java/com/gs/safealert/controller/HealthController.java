package com.gs.safealert.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

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

