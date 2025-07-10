package com.example.doloApp.controller;



import com.example.doloApp.dto.CreateTripRequest;
import com.example.doloApp.model.Trip;
import com.example.doloApp.service.TripService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/create")
    public ResponseEntity<Trip> createTrip(@RequestBody CreateTripRequest request, HttpServletRequest httpRequest) {
        String travelerId = (String) httpRequest.getAttribute("uid");  // UID from FirebaseTokenFilter
        Trip savedTrip = tripService.createTrip(request, travelerId);
        return ResponseEntity.ok(savedTrip);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(
            @RequestParam String fromCity,
            @RequestParam String toCity,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate) {
        List<Trip> result = tripService.searchTrips(fromCity, toCity, travelDate.minusDays(1));
        return ResponseEntity.ok(result);
    }
}

