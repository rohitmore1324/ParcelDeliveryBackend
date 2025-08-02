package com.example.doloApp.controller;


import com.example.doloApp.dto.CreatePackageRequest;
import com.example.doloApp.model.Package;
import com.example.doloApp.service.PackageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/create")
    public ResponseEntity<Package> createPackage(@RequestBody CreatePackageRequest request, HttpServletRequest httpRequest) {
        String senderId = (String) httpRequest.getAttribute("uid");
        Package created = packageService.createPackage(request, senderId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/match")
    public ResponseEntity<List<Package>> matchPackages(
            @RequestParam String fromCity,
            @RequestParam String toCity) {
        List<Package> result = packageService.findMatchingPackages(fromCity, toCity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/public/packages/match")
    public ResponseEntity<List<Package>> publicMatchPackages(
            @RequestParam String fromCity,
            @RequestParam String toCity) {
        List<Package> result = packageService.findMatchingPackages(fromCity, toCity);
        return ResponseEntity.ok(result);
    }
}
