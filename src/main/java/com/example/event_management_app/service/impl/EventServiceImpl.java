package com.example.event_management_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.event_management_app.exception.NotFoundException;
import com.example.event_management_app.model.dto.request.EventRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.dto.response.PaginationInfo;
import com.example.event_management_app.model.entity.Event;
import com.example.event_management_app.repository.EventAttendeeRepository;
import com.example.event_management_app.repository.EventRepository;
import com.example.event_management_app.service.EventService;
import com.example.event_management_app.util.PaginationTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    final private EventRepository eventRepository;
    final private EventAttendeeRepository eventAttendeeRepository;

    @Override
    public PagedResponse<Event> getAllEvents(Long page, Long size) {
        Long offset = (page - 1) * size;
        Long totalCount = eventRepository.getEventTotalCount();
        List<Event> events = eventRepository.getAllEvents(offset, size);
        PaginationInfo paginationInfo = PaginationTools.createPaginationInfo(totalCount, page, size);
        PagedResponse<Event> response = new PagedResponse<>(events, paginationInfo);
        return response;
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("Event with ID " + eventId + " does not exist");
        }

        return event;
    }

    @Override
    public Event saveEvent(EventRequest request) {
        Event event = eventRepository.saveEvent(request);
        if (!request.getAttendeeIds().isEmpty()) {
            for (Long attendeeId : request.getAttendeeIds()) {
                eventAttendeeRepository.saveEventAttendee(event.getEventId(), attendeeId);
            }
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event event = eventRepository.updateEventById(eventId, request);

        if (event == null) {
            throw new NotFoundException("Event with ID " + eventId + " does not exist");
        }

        if (!request.getAttendeeIds().isEmpty()) {
            eventAttendeeRepository.deleteEventAttendeeByEventId(eventId);
            for (Long attendeeId : request.getAttendeeIds()) {
                eventAttendeeRepository.saveEventAttendee(eventId, attendeeId);
            }
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event deleteEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("Event with ID " + eventId + " does not exist");
        }

        eventRepository.deleteEventById(eventId);
        return event;
    }

}
