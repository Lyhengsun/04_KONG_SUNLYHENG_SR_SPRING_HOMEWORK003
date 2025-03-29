CREATE DATABASE event_management_db;

CREATE TABLE attendees (
    attendee_id SERIAL PRIMARY KEY,
    attendee_name VARCHAR(50) NOT NULL,
    email VARCHAR(50)
)

CREATE TABLE venues (
    venue_id SERIAL PRIMARY KEY,
    venue_name VARCHAR(50),
    location VARCHAR(50)
)

CREATE TABLE events (
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR(50),
    event_date TIMESTAMP WITH TIME ZONE,
    venue_id INT NOT NULL,
    CONSTRAINT fk_venue_id FOREIGN KEY (venue_id) REFERENCES venues (venue_id) ON DELETE CASCADE 
);

CREATE TABLE event_attendee (
    id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    attendee_id INT NOT NULL,
    UNIQUE(event_id, attendee_id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
    CONSTRAINT fk_attendee_id FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE
);