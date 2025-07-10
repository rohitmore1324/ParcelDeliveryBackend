package com.example.doloApp.service;

import com.example.doloApp.dto.CreateUserRequest;
import com.example.doloApp.model.User;
import com.example.doloApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerOrUpdateUser(String uid, CreateUserRequest request) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findById(uid);
        
        User user;
        if (existingUser.isPresent()) {
            // Update existing user
            user = existingUser.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setRole(request.getRole());
        } else {
            // Create new user
            user = User.builder()
                    .id(uid)
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .role(request.getRole())
                    .build();
        }
        
        return userRepository.save(user);
    }

    public Optional<User> getUserByUid(String uid) {
        return userRepository.findById(uid);
    }

    public boolean userExists(String uid) {
        return userRepository.existsById(uid);
    }

    public User createUserIfNotExists(String uid, String email, String name) {
        Optional<User> existingUser = userRepository.findById(uid);
        
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        
        User newUser = User.builder()
                .id(uid)
                .email(email)
                .name(name != null ? name : "User")
                .role("user") // default role
                .build();
        
        return userRepository.save(newUser);
    }
}

