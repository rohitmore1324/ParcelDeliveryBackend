package com.example.doloApp.repository;

import com.example.doloApp.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByTripId(String tripId);
    List<Booking> findByPackageId(String packageId);
    List<Booking> findByUserId(String userId);
}

