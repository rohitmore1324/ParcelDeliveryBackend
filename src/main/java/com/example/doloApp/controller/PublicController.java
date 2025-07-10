package com.example.doloApp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "message", "Dolo API is running");
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
            "name", "Dolo Travel API",
            "version", "1.0.0",
            "description", "Travel and booking management system with Firebase authentication"
        );
    }
} 