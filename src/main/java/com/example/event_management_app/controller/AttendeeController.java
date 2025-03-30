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

import com.example.event_management_app.model.dto.request.AttendeeRequest;
import com.example.event_management_app.model.dto.response.ApiResponse;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Attendee;
import com.example.event_management_app.service.AttendeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    final private AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<Attendee>>> getAllAttendees(
            @Valid @Min(message = "page number needed to be bigger than 0", value = 1) @RequestParam(defaultValue = "1") Long page,
            @Valid @Min(message = "page size needed to be bigger than 0", value = 1) @RequestParam(defaultValue = "5") Long size) {
        PagedResponse<Attendee> pagedResponse = attendeeService.getAllAttendees(page, size);
        ApiResponse<PagedResponse<Attendee>> response = ApiResponse.<PagedResponse<Attendee>>builder()
                .message("Get all Attendees Successfully")
                .payload(pagedResponse)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(
            @Valid @Min(message = "Attendee ID number needed to be bigger than 0", value = 1) @PathVariable("attendee-id") Long attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Get attendee with ID: " + attendeeId + " successfully")
                .payload(attendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> saveAttendee(@RequestBody @Valid AttendeeRequest request) {
        Attendee attendee = attendeeService.saveAttendee(request);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Save new attendee successfully")
                .payload(attendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(
            @PathVariable("attendee-id") Long attendeeId,
            @Valid @RequestBody AttendeeRequest request) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, request);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update Attendee with ID: " + attendeeId + " successfully")
                .payload(attendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(
            @Valid @Min(message = "Attendee ID number needed to be bigger than 0", value = 1) @PathVariable("attendee-id") Long attendeeId) {
        Attendee attendee = attendeeService.deleteAttendeeById(attendeeId);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Delete Attendee with ID: " + attendeeId + " successfully")
                .payload(attendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
