package com.example.event_management_app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.event_management_app.model.dto.request.VenueRequest;
import com.example.event_management_app.model.entity.Venue;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    @Select("""
            SELECT * FROM venues OFFSET #{offset} LIMIT #{limit};
            """)
    public List<Venue> getAllVenues(@Param("offset") Long offset, @Param("limit") Long limit);

    @Select("""
            SELECT COUNT(venue_id) venues_count FROM venues;
            """)
    public Long getVenueTotalCount();

    @ResultMap("venueMapper")
    @Select("""
            SELECT * FROM venues WHERE venue_id = #{venue_id}
            """)
    public Venue getVenueById(@Param("venue_id") Long venueId);

    @ResultMap("venueMapper")
    @Select("""
            INSERT INTO venues (venue_name, location)
            VALUES (#{req.venueName}, #{req.location})
            RETURNING *;
            """)
    public Venue saveVenue(@Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
            UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location}
            WHERE venue_id = #{venue_id}
            RETURNING *;
            """)
    public Venue updateVenueById(@Param("venue_id") Long venueId, @Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
            DELETE FROM venues WHERE venue_id = #{venue_id}
            RETURNING *;
            """)
    public Venue deleteVenueById(Long venueId);
}
