package com.example.event_management_app.service;

import com.example.event_management_app.model.dto.request.AttendeeRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.entity.Attendee;

public interface AttendeeService {

    public PagedResponse<Attendee> getAllAttendees(Long page, Long size);

    public Attendee getAttendeeById(Long attendeeId);

    public Attendee saveAttendee(AttendeeRequest request);

    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    public Attendee deleteAttendeeById(Long attendeeId);
}
