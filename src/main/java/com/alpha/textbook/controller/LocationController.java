package com.alpha.textbook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.textbook.dto.Location.CreateLocationRequest;
import com.alpha.textbook.dto.Location.LocationResponse;
import com.alpha.textbook.service.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationResponse> addNewLocation(
            @RequestBody CreateLocationRequest createLocationRequest) {
        return null;
    }

    @GetMapping
    public ResponseEntity<LocationResponse> getAllLocation() {
        return null;
    }
}
