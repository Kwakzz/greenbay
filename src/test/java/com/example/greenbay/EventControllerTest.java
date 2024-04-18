package com.example.greenbay;
import com.example.greenbay.controllers.EventController;
import com.example.greenbay.models.Event;
import com.example.greenbay.repositories.EventRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventById() {
        String eventId = "1";
        Event mockEvent = new Event("organizerId", "Test Event", "Description", "12:00", "2024-12-31", "Location");
        mockEvent.setId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(mockEvent));

        Event event = eventController.getEvent(eventId);

        assertNotNull(event);
        assertEquals(eventId, event.getId());
        assertEquals("Test Event", event.getTitle());
        assertEquals("Description", event.getDescription());
        assertEquals("2024-12-31", event.getDate());
        assertEquals("12:00", event.getTime());
        assertEquals("Location", event.getLocation());
        assertEquals("organizerId", event.getOrganizerId());
    }

    @Test
    void testGetAllEvents() {
        // Mocking data
        List<Event> mockEvents = new ArrayList<>();
        mockEvents.add(new Event("organizerId", "Event 1", "Description 1", "12:00", "2024-12-31", "Location"));
        mockEvents.add(new Event("organizerId", "Event 2", "Description 2", "12:00", "2024-12-31", "Location"));

        // Mocking repository behavior
        when(eventRepository.findAll()).thenReturn(mockEvents);

        // Call the controller method
        Iterable<Event> events = eventController.getEvents();

        // Verify the result
        assertNotNull(events);
        assertEquals(2, ((List<Event>) events).size());
    }

    @Test
    void testCreateEvent() {
        Event newEvent = new Event("organizerId", "Test Event", "Description", "12:00", "2024-12-31", "Location");

        when(eventRepository.save(newEvent)).thenReturn(newEvent);

        ResponseEntity<Event> responseEntity = eventController.createEvent(newEvent);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newEvent, responseEntity.getBody());
        verify(eventRepository, times(1)).save(newEvent);
    }
}
