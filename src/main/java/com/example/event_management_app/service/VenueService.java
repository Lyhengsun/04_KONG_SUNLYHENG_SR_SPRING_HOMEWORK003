package com.example.event_management_app.service;

import com.example.event_management_app.model.dto.request.VenueRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Venue;

public interface VenueService {

    public PagedResponse<Venue> getAllVenues(Long page, Long size);

    public Venue getVenueById(Long venueId);

    public Venue saveVenue(VenueRequest request);

    public Venue updateVenueById(Long venueId, VenueRequest request);

    public Venue deleteVenueById(Long venueId);

}
