package com.example.event_management_app.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee Name is required")
    @Size(min = 3, max = 50, message = "Attendee Name need to be between 3 to 50 characters")
    @Pattern(regexp = "^([\\w]+ )+[\\w]+$|^[\\w]+$", message = "Attendee Name can only contains letter and one space between words")
    private String attendeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Format for Email. ex: name@gmail.com")
    private String email;
}
