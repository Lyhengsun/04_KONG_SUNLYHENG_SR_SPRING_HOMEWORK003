package com.example.event_management_app.model.entity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private Long eventId;
    private String eventName;
    private OffsetDateTime eventDate;
    private Venue venue;
    private List<Attendee> attendees;

    public void setEventDate(OffsetDateTime eventDate) {
        this.eventDate = eventDate.withOffsetSameInstant(ZoneOffset.of("+00:00"));
    }
}
