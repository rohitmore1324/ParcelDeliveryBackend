package com.example.doloApp.service;


import com.example.doloApp.dto.CreateTripRequest;
import com.example.doloApp.model.Trip;
import com.example.doloApp.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(CreateTripRequest request, String travelerId) {
        Trip trip = Trip.builder()
                .travelerId(travelerId)
                .fromCity(request.getFromCity())
                .toCity(request.getToCity())
                .travelDate(request.getTravelDate())
                .spaceAvailable(request.isSpaceAvailable())
                .build();

        return tripRepository.save(trip);
    }

    public List<Trip> searchTrips(String fromCity, String toCity, LocalDate afterDate) {
        return tripRepository.findByFromCityAndToCityAndTravelDateAfter(fromCity, toCity, afterDate);
    }
}
