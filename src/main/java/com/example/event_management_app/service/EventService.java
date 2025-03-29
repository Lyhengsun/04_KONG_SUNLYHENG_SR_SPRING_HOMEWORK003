package com.example.event_management_app.service;

import com.example.event_management_app.model.dto.request.EventRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Event;

public interface EventService {

    public PagedResponse<Event> getAllEvents(Long page, Long size);

    public Event getEventById(Long eventId);

    public Event saveEvent(EventRequest request);

    public Event updateEventById(Long eventId, EventRequest request);

    public Event deleteEventById(Long eventId);

}
