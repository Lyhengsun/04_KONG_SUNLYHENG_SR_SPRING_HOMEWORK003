package com.example.event_management_app.model.dto.request;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    private String eventName;
    private OffsetDateTime eventDate;
    private Long venueId;
    private List<Long> attendeeIds;
}
