package com.example.event_management_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    @NotBlank(message = "Venue Name is required")
    @Size(min = 3, max = 50, message = "Venue Name need to be between 3 to 50 characters")
    @Pattern(regexp = "^([\\w]+ )+[\\w]+$|^[\\w]+$", message = "Venue Name can only contains letter and one space between words")
    private String venueName;

    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location need to be between 3 to 50 characters")
    @Pattern(regexp = "^([\\w]+ )+[\\w]+$|^[\\w]+$", message = "Location can only contains letter and one space between words")
    private String location;
}
