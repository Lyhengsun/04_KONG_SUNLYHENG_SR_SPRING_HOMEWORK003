package com.example.event_management_app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.event_management_app.model.entity.Attendee;

@Mapper
public interface EventAttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
            SELECT a.attendee_id, a.attendee_name, email
            FROM event_attendee ea
            INNER JOIN attendees a
            ON ea.attendee_id = a.attendee_id
            WHERE event_id = #{event_id};
            """)
    public List<Attendee> getAttendeesByEventId(@Param("event_id") Long eventId);

    @Insert("""
            INSERT INTO event_attendee (event_id, attendee_id)
            VALUES (#{event_id}, #{attendee_id});
            """)
    public void saveEventAttendee(@Param("event_id") Long eventId, @Param("attendee_id") Long attendeeId);

    @Delete("""
            DELETE FROM event_attendee
            WHERE event_id = #{event_id};
            """)
    public void deleteEventAttendeeByEventId(@Param("event_id") Long eventId);
}
