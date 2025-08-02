package com.example.doloApp.service;

import com.example.doloApp.dto.CreateTripRequest;
import com.example.doloApp.model.Trip;
import com.example.doloApp.repository.TripRepository;
import com.example.doloApp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(CreateTripRequest request, String travelerId) {
        // ✅ Business logic validation
        validateTripRequest(request);
        
        // ✅ Check for duplicate trips
        if (tripRepository.existsByTravelerIdAndFromCityAndToCityAndTravelDate(
                travelerId, request.getFromCity(), request.getToCity(), request.getTravelDate())) {
            throw new IllegalArgumentException("A trip with this route and date already exists");
        }

        Trip trip = Trip.builder()
                .travelerId(travelerId)
                .fromCity(request.getFromCity())
                .toCity(request.getToCity())
                .travelDate(request.getTravelDate())
                .spaceAvailable(request.isSpaceAvailable())
                .build();

        return tripRepository.save(trip);
    }

    public List<Trip> searchTrips(String fromCity, String toCity, LocalDate travelDate) {
        // ✅ Fix: Search for trips on or after the specified date
        return tripRepository.findByFromCityAndToCityAndTravelDateGreaterThanEqual(fromCity, toCity, travelDate);
    }

    public Trip getTripById(String tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", tripId));
    }

    public List<Trip> getTripsByTravelerId(String travelerId) {
        return tripRepository.findByTravelerId(travelerId);
    }

    private void validateTripRequest(CreateTripRequest request) {
        if (request.getFromCity().equalsIgnoreCase(request.getToCity())) {
            throw new IllegalArgumentException("From and to cities cannot be the same");
        }
        
        if (request.getTravelDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Travel date cannot be in the past");
        }
    }
}
