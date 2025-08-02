//package com.example.doloApp.controller;
//
//import com.example.doloApp.dto.CreateUserRequest;
//import com.example.doloApp.model.User;
//import com.example.doloApp.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody CreateUserRequest request,
//                                             HttpServletRequest httpRequest) {
//        String uid = (String) httpRequest.getAttribute("uid");
//        User saved = userService.registerOrUpdateUser(uid, request);
//        return ResponseEntity.ok(saved);
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<?> getUser(HttpServletRequest httpRequest) {
//        String uid = (String) httpRequest.getAttribute("uid");
//        String email = (String) httpRequest.getAttribute("email");
//
//        // Get full user info from DB
//        return userService.getUserByUid(uid)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.ok(Map.of("uid", uid, "email", email)));
//    }
//}


package com.example.doloApp.controller;
//
import com.example.doloApp.dto.CreateUserRequest;
import com.example.doloApp.model.User;
import com.example.doloApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody CreateUserRequest request,
                                             HttpServletRequest httpRequest) {
        String uid = (String) httpRequest.getAttribute("uid");
        User saved = userService.registerOrUpdateUser(uid, request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser(HttpServletRequest httpRequest) {
        String uid = (String) httpRequest.getAttribute("uid");
        String email = (String) httpRequest.getAttribute("email");

        Optional<User> optionalUser = userService.getUserByUid(uid);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.ok(Map.of("uid", uid, "email", email));
        }
    }
}
