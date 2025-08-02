package com.example.doloApp.controller;
//
import com.example.doloApp.dto.CreateUserRequest;
import com.example.doloApp.model.User;
import com.example.doloApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                // Token is already verified by FirebaseTokenFilter
                // This endpoint just confirms the token is valid
                return ResponseEntity.ok(Map.of(
                    "message", "Token is valid",
                    "status", "authenticated"
                ));
            } catch (Exception e) {
                return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid token",
                    "status", "unauthenticated"
                ));
            }
        }
        return ResponseEntity.status(401).body(Map.of(
            "message", "No token provided",
            "status", "unauthenticated"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest request,
                                        @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of(
                "message", "Authentication required",
                "status", "unauthenticated"
            ));
        }

        try {
            String token = authHeader.substring(7);
            // Extract UID from token (this would be done by the filter in a real scenario)
            // For now, we'll use the email as a placeholder
            String uid = request.getEmail(); // In real implementation, extract from Firebase token
            
            User savedUser = userService.registerOrUpdateUser(uid, request);
            return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "user", savedUser,
                "status", "success"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", "Registration failed: " + e.getMessage(),
                "status", "error"
            ));
        }
    }
} 