package com.alpha.textbook.service;

import org.springframework.stereotype.Service;

import com.alpha.textbook.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
}
