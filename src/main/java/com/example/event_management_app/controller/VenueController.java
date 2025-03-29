package com.example.event_management_app.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_management_app.model.dto.request.VenueRequest;
import com.example.event_management_app.model.dto.response.ApiResponse;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Venue;
import com.example.event_management_app.service.VenueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    final private VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size) {
        PagedResponse<Venue> pagedResponse = venueService.getAllVenues(page, size);
        ApiResponse<PagedResponse<Venue>> response = ApiResponse.<PagedResponse<Venue>>builder()
                .message("Get all venues succesfully")
                .payload(pagedResponse)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Long venueId) {
        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Get venue with ID: " + venueId + " successfully")
                .payload(venue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> saveVenue(@RequestBody VenueRequest request) {
        Venue venue = venueService.saveVenue(request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Save new venue successfully")
                .payload(venue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") Long venueId,
            @RequestBody VenueRequest request) {
        Venue venue = venueService.updateVenueById(venueId, request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update venue with ID: " + venueId + " successfully")
                .payload(venue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Long venueId) {
        Venue venue = venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Delete venue with ID: " + venueId + " successfully")
                .payload(venue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
