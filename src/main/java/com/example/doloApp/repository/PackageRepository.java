package com.example.doloApp.repository;


import com.example.doloApp.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PackageRepository extends MongoRepository<Package, String> {
    List<Package> findByFromCityAndToCityAndStatus(String fromCity, String toCity, String status);
}
