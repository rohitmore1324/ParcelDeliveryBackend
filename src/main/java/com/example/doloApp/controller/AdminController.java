
//
//import com.example.doloApp.model.User;
//import com.example.doloApp.model.Trip;
//import com.example.doloApp.model.Package;
//import com.example.doloApp.model.Booking;
//import com.example.doloApp.repository.UserRepository;
//import com.example.doloApp.repository.TripRepository;
//import com.example.doloApp.repository.PackageRepository;
//import com.example.doloApp.repository.BookingRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin")
//public class AdminController {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private TripRepository tripRepository;
//    @Autowired
//    private PackageRepository packageRepository;
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    private boolean isAdmin(HttpServletRequest request) {
//        Object role = request.getAttribute("role");
//        if (role != null && role.toString().equals("admin")) {
//            return true;
//        }
//        return false;
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        List<User> users = userRepository.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/trips")
//    public ResponseEntity<?> getAllTrips(HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        List<Trip> trips = tripRepository.findAll();
//        return ResponseEntity.ok(trips);
//    }
//
//    @GetMapping("/packages")
//    public ResponseEntity<?> getAllPackages(HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        List<Package> packages = packageRepository.findAll();
//        return ResponseEntity.ok(packages);
//    }
//
//    @GetMapping("/bookings")
//    public ResponseEntity<?> getAllBookings(HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        List<Booking> bookings = bookingRepository.findAll();
//        return ResponseEntity.ok(bookings);
//    }
//
//    @PutMapping("/user/{id}/role")
//    public ResponseEntity<?> updateUserRole(@PathVariable String id, @RequestParam String role, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setRole(role);
//                    userRepository.save(user);
//                    return ResponseEntity.ok(user);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable String id, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        userRepository.deleteById(id);
//        return ResponseEntity.ok("User deleted");
//    }
//
//    @PutMapping("/trip/{id}")
//    public ResponseEntity<?> updateTrip(@PathVariable String id, @RequestBody Trip updatedTrip, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        return tripRepository.findById(id)
//                .map(trip -> {
//                    updatedTrip.setId(id);
//                    tripRepository.save(updatedTrip);
//                    return ResponseEntity.ok(updatedTrip);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/trip/{id}")
//    public ResponseEntity<?> deleteTrip(@PathVariable String id, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        if (!tripRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        tripRepository.deleteById(id);
//        return ResponseEntity.ok("Trip deleted");
//    }
//
//    @PutMapping("/package/{id}")
//    public ResponseEntity<?> updatePackage(@PathVariable String id, @RequestBody Package updatedPackage, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        return packageRepository.findById(id)
//                .map(pack -> {
//                    updatedPackage.setId(id);
//                    packageRepository.save(updatedPackage);
//                    return ResponseEntity.ok(updatedPackage);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/package/{id}")
//    public ResponseEntity<?> deletePackage(@PathVariable String id, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        if (!packageRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        packageRepository.deleteById(id);
//        return ResponseEntity.ok("Package deleted");
//    }
//
//    @PutMapping("/booking/{id}")
//    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody Booking updatedBooking, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        return bookingRepository.findById(id)
//                .map(booking -> {
//                    updatedBooking.setId(id);
//                    bookingRepository.save(updatedBooking);
//                    return ResponseEntity.ok(updatedBooking);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/booking/{id}")
//    public ResponseEntity<?> deleteBooking(@PathVariable String id, HttpServletRequest request) {
//        if (!isAdmin(request)) {
//            return ResponseEntity.status(403).body("Forbidden: Admins only");
//        }
//        if (!bookingRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        bookingRepository.deleteById(id);
//        return ResponseEntity.ok("Booking deleted");
//    }
//} 

package com.example.doloApp.controller;

//import com.example.doloApp.model.*;
import com.example.doloApp.model.User;
import com.example.doloApp.model.Trip;
import com.example.doloApp.model.Package;
import com.example.doloApp.model.Booking;
import com.example.doloApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//admin
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private TripRepository tripRepository;
    @Autowired private PackageRepository packageRepository;
    @Autowired private BookingRepository bookingRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/trips")
    public ResponseEntity<?> getAllTrips() {
        return ResponseEntity.ok(tripRepository.findAll());
    }

    @GetMapping("/packages")
    public ResponseEntity<?> getAllPackages() {
        return ResponseEntity.ok(packageRepository.findAll());
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @PutMapping("/user/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable String id, @RequestParam String role) {
        return userRepository.findById(id)
            .map(user -> {
                user.setRole(role);
                userRepository.save(user);
                return ResponseEntity.ok(user);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping("/trip/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable String id, @RequestBody Trip updatedTrip) {
        return tripRepository.findById(id)
            .map(trip -> {
                updatedTrip.setId(id);
                tripRepository.save(updatedTrip);
                return ResponseEntity.ok(updatedTrip);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/trip/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable String id) {
        if (!tripRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tripRepository.deleteById(id);
        return ResponseEntity.ok("Trip deleted");
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<?> updatePackage(@PathVariable String id, @RequestBody Package updatedPackage) {
        return packageRepository.findById(id).map(pack -> { updatedPackage.setId(id); packageRepository.save(updatedPackage);
                return ResponseEntity.ok(updatedPackage);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<?> deletePackage(@PathVariable String id) {
        if (!packageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        packageRepository.deleteById(id);
        return ResponseEntity.ok("Package deleted");
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody Booking updatedBooking) {
        return bookingRepository.findById(id)
            .map(booking -> {
                updatedBooking.setId(id);
                bookingRepository.save(updatedBooking);
                return ResponseEntity.ok(updatedBooking);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id) {
        if (!bookingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.ok("Booking deleted");
    }
}
