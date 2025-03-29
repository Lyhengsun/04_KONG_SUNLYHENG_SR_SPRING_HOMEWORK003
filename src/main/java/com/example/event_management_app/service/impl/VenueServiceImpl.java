package com.example.event_management_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.event_management_app.model.dto.request.VenueRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.dto.response.PaginationInfo;
import com.example.event_management_app.model.entity.Venue;
import com.example.event_management_app.repository.VenueRepository;
import com.example.event_management_app.service.VenueService;
import com.example.event_management_app.util.PaginationTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    final private VenueRepository venueRepository;

    @Override
    public PagedResponse<Venue> getAllVenues(Long page, Long size) {
        Long offset = (page - 1) * size;
        Long totalCount = venueRepository.getVenueTotalCount();
        List<Venue> venues = venueRepository.getAllVenues(offset, size);
        PaginationInfo paginationInfo = PaginationTools.createPaginationInfo(totalCount, page, size);
        return new PagedResponse<>(venues, paginationInfo);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        return venue;
    }

    @Override
    public Venue saveVenue(VenueRequest request) {
        Venue venue = venueRepository.saveVenue(request);
        return venueRepository.getVenueById(venue.getVenueId());
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        Venue venue = venueRepository.updateVenueById(venueId, request);
        return venue;
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        Venue venue = venueRepository.deleteVenueById(venueId);
        return venue;
    }

}
