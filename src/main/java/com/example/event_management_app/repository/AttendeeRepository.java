package com.example.event_management_app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.event_management_app.model.dto.request.AttendeeRequest;
import com.example.event_management_app.model.entity.Attendee;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
            SELECT * FROM attendees OFFSET #{offset} LIMIT #{limit};
            """)
    public List<Attendee> getAllAttendees(@Param("offset") Long offset, @Param("limit") Long limit);

    @Select("""
            SELECT COUNT(attendee_id) attendees_count FROM attendees;
            """)
    public Long getAttendeeTotalCount();

    @ResultMap("attendeeMapper")
    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{attendee_id};
            """)
    public Attendee getAttendeeById(@Param("attendee_id") Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
            INSERT INTO attendees (attendee_name, email)
            VALUES (#{req.attendeeName}, #{req.email})
            RETURNING *;
            """)
    public Attendee saveAttendee(@Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select("""
            UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email}
            WHERE attendee_id = #{attendee_id}
            RETURNING *;
            """)
    public Attendee updateAttendeeById(@Param("attendee_id") Long attendeeId, @Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select("""
            DELETE FROM attendees
            WHERE attendee_id = #{attendee-id}
            RETURNING *;
            """)
    public Attendee deleteAttendeeById(@Param("attendee-id") Long attendeeId);
}
