package com.example.doloApp.service;



import com.example.doloApp.dto.CreatePackageRequest;
import com.example.doloApp.model.Package;
import com.example.doloApp.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public Package createPackage(CreatePackageRequest request, String senderId) {
        Package pack = Package.builder()
                .senderId(senderId)
                .fromCity(request.getFromCity())
                .toCity(request.getToCity())
                .weight(request.getWeight())
                .reward(request.getReward())
                .imageUrl(request.getImageUrl())
                .status("open")
                .build();
        return packageRepository.save(pack);
    }

    public List<Package> findMatchingPackages(String fromCity, String toCity) {
        return packageRepository.findByFromCityAndToCityAndStatus(fromCity, toCity, "open");
    }
}
