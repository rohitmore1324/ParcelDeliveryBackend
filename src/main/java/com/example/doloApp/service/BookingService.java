package com.example.doloApp.service;


import com.example.doloApp.dto.CreateBookingRequest;
import com.example.doloApp.model.Booking;
import com.example.doloApp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(CreateBookingRequest request) {
        Booking booking = Booking.builder()
                .tripId(request.getTripId())
                .packageId(request.getPackageId())
                .userId(request.getUserId())
                .status("pending")
                .createdAt(LocalDateTime.now())
                .build();
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForTrip(String tripId) {
        return bookingRepository.findByTripId(tripId);
    }

    public List<Booking> getBookingsByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Optional<Booking> updateStatus(String bookingId, String status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        bookingOpt.ifPresent(booking -> {
            booking.setStatus(status);
            bookingRepository.save(booking);
        });
        return bookingOpt;
    }

    public Optional<Booking> updateStatus(String bookingId, String status, String userId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            
            // Check if user is authorized to update this booking
            // User can update if they are the booking owner or the trip owner
            if (booking.getUserId().equals(userId)) {
                booking.setStatus(status);
                return Optional.of(bookingRepository.save(booking));
            }
        }
        
        return Optional.empty();
    }
}

