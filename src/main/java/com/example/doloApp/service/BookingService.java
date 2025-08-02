package com.example.doloApp.service;

import com.example.doloApp.dto.CreateBookingRequest;
import com.example.doloApp.model.Booking;
import com.example.doloApp.model.Trip;
import com.example.doloApp.repository.BookingRepository;
import com.example.doloApp.repository.TripRepository;
import com.example.doloApp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private TripRepository tripRepository;

    public Booking createBooking(CreateBookingRequest request) {
        // ✅ Validate that trip exists
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", request.getTripId()));
        
        // ✅ Check if trip has space available
        if (!trip.isSpaceAvailable()) {
            throw new IllegalArgumentException("Trip is full, no space available");
        }
        
        // ✅ Check if package is still open for booking
        // This would require PackageService injection - simplified for now
        
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
            
            // ✅ Check if user is authorized to update this booking
            // User can update if they are the booking owner OR the trip owner
            boolean isBookingOwner = booking.getUserId().equals(userId);
            boolean isTripOwner = false;
            
            // Check if user is trip owner
            Optional<Trip> tripOpt = tripRepository.findById(booking.getTripId());
            if (tripOpt.isPresent()) {
                isTripOwner = tripOpt.get().getTravelerId().equals(userId);
            }
            
            if (isBookingOwner || isTripOwner) {
                // ✅ Validate status transition
                if (isValidStatusTransition(booking.getStatus(), status)) {
                    booking.setStatus(status);
                    return Optional.of(bookingRepository.save(booking));
                } else {
                    throw new IllegalArgumentException("Invalid status transition from " + booking.getStatus() + " to " + status);
                }
            }
        }
        
        return Optional.empty();
    }
    
    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        // ✅ Define valid status transitions
        return switch (currentStatus) {
            case "pending" -> newStatus.equals("accepted") || newStatus.equals("rejected");
            case "accepted" -> newStatus.equals("completed") || newStatus.equals("cancelled");
            case "rejected" -> false; // Cannot change from rejected
            case "completed" -> false; // Cannot change from completed
            case "cancelled" -> false; // Cannot change from cancelled
            default -> false;
        };
    }
}

