package com.example.event_management_app.model.dto.request;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {

    @NotBlank(message = "Event Name is required")
    @Size(min = 3, max = 50, message = "Event Name need to be between 3 to 50 characters")
    @Pattern(regexp = "^([\\w]+ )+[\\w]+$|^[\\w]+$", message = "Event Name can only contains letter and one space between words")
    private String eventName;

    @NotNull(message = "Event Date is required")
    private OffsetDateTime eventDate;

    @NotNull(message = "Venue ID is required")
    @Positive(message = "Venue ID needed to be a postive integer")
    private Long venueId;

    @NotEmpty(message = "List Attendee IDs can't be empty")
    private List<@Positive(message = "Attendee ID needed to be a postive integer") Long> attendeeIds;
}
