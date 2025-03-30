package com.example.event_management_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.event_management_app.exception.NotFoundException;
import com.example.event_management_app.model.dto.request.AttendeeRequest;
import com.example.event_management_app.model.dto.response.PagedResponse;
import com.example.event_management_app.model.dto.response.PaginationInfo;
import com.example.event_management_app.model.entity.Attendee;
import com.example.event_management_app.repository.AttendeeRepository;
import com.example.event_management_app.service.AttendeeService;
import com.example.event_management_app.util.PaginationTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    final private AttendeeRepository attendeeRepository;

    @Override
    public PagedResponse<Attendee> getAllAttendees(Long page, Long size) {
        Long offset = (page - 1) * size;
        Long totalCount = attendeeRepository.getAttendeeTotalCount();
        List<Attendee> attendees = attendeeRepository.getAllAttendees(offset, size);
        PaginationInfo paginationInfo = PaginationTools.createPaginationInfo(totalCount, page, size);
        PagedResponse<Attendee> response = new PagedResponse<>(attendees, paginationInfo);
        return response;
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + attendeeId + " does not exist");
        }

        return attendee;
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {
        Attendee attendee = attendeeRepository.saveAttendee(request);
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        Attendee attendee = attendeeRepository.updateAttendeeById(attendeeId, request);

        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + attendeeId + " does not exist");
        }

        return attendee;
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.deleteAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + attendeeId + " does not exist");
        }

        return attendee;
    }
}
