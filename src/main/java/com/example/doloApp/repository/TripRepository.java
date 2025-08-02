package com.example.doloApp.repository;


import com.example.doloApp.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findByFromCityAndToCityAndTravelDateGreaterThanEqual(String fromCity, String toCity, LocalDate travelDate);
    List<Trip> findByTravelerId(String travelerId);
    boolean existsByTravelerIdAndFromCityAndToCityAndTravelDate(String travelerId, String fromCity, String toCity, LocalDate travelDate);
}
