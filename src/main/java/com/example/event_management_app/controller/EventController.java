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

import com.example.event_management_app.model.dto.request.EventRequest;
import com.example.event_management_app.model.dto.response.ApiResponse;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Event;
import com.example.event_management_app.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    final private EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<Event>>> getAllEvents(@RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size) {
        PagedResponse<Event> pagedResponse = eventService.getAllEvents(page, size);
        ApiResponse<PagedResponse<Event>> response = ApiResponse.<PagedResponse<Event>>builder()
                .message("Get all Events Successfully")
                .payload(pagedResponse)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get Event with ID: " + eventId + " Successfully")
                .payload(event)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> saveEvent(@RequestBody EventRequest request) {
        Event event = eventService.saveEvent(request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Save new event Successfully")
                .payload(event)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable("event-id") Long eventId,
            @RequestBody EventRequest request) {
        Event event = eventService.updateEventById(eventId, request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update event with ID: " + eventId + " successfully")
                .payload(event)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Delete event with ID: " + eventId + " successfully")
                .payload(event)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
