package com.example.doloApp.controller;

import com.example.doloApp.dto.CreateBookingRequest;
import com.example.doloApp.model.Booking;
import com.example.doloApp.model.User;
import com.example.doloApp.service.BookingService;
import com.example.doloApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request,
                                         HttpServletRequest httpRequest) {
        String uid = (String) httpRequest.getAttribute("uid");
        
        // Verify user exists
        Optional<User> user = userService.getUserByUid(uid);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", "User not found. Please register first.",
                "status", "error"
            ));
        }
        
        // Set the user ID in the booking request
        request.setUserId(uid);
        
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<Booking>> getMyBookings(HttpServletRequest httpRequest) {
        String uid = (String) httpRequest.getAttribute("uid");
        return ResponseEntity.ok(bookingService.getBookingsByUserId(uid));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Booking>> getBookingsByTrip(@PathVariable String tripId) {
        return ResponseEntity.ok(bookingService.getBookingsForTrip(tripId));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String bookingId,
            @RequestParam String status,
            HttpServletRequest httpRequest) {
        
        String uid = (String) httpRequest.getAttribute("uid");
        
        // Only allow users to update their own bookings or trip owners
        return bookingService.updateStatus(bookingId, status, uid)
                .map(updated -> ResponseEntity.ok(updated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

