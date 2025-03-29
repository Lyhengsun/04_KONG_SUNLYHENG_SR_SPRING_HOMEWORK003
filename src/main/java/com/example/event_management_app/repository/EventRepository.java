package com.example.event_management_app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.event_management_app.model.dto.request.EventRequest;
import com.example.event_management_app.model.entity.Event;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "com.example.event_management_app.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.example.event_management_app.repository.EventAttendeeRepository.getAttendeesByEventId"))
    })
    @Select("""
            SELECT * FROM events OFFSET #{offset} Limit #{limit};
            """)
    public List<Event> getAllEvents(@Param("offset") Long offset, @Param("limit") Long limit);

    @Select("""
            SELECT COUNT(event_id) FROM events;
            """)
    public Long getEventTotalCount();

    @ResultMap("eventMapper")
    @Select("""
            SELECT * FROM events WHERE event_id = #{event_id};
            """)
    public Event getEventById(@Param("event_id") Long eventId);

    @ResultMap("eventMapper")
    @Select("""
            INSERT INTO events (event_name, event_date, venue_id)
            VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId})
            RETURNING *;
            """)
    public Event saveEvent(@Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
            UPDATE events
            SET event_name=#{req.eventName}, event_date=#{req.eventDate}, venue_id=#{req.venueId}
            WHERE event_id = #{event_id}
            RETURNING *;
            """)
    public Event updateEventById(@Param("event_id") Long eventId, @Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
            DELETE FROM events WHERE event_id = #{event_id}
            RETURNING *;
            """)
    public Event deleteEventById(@Param("event_id") Long eventId);
}
