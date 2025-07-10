package com.example.doloApp.repository;


import com.example.doloApp.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findByFromCityAndToCityAndTravelDateAfter(String fromCity, String toCity, LocalDate travelDate);
}
