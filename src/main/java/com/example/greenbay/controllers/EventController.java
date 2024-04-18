package com.example.greenbay.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.greenbay.models.Event;
import com.example.greenbay.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("?id={id}")
    public Event getEvent(@PathVariable String id) {
        return eventRepository.findById(id).orElse(null);
    }

    @GetMapping("")
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("?organizerId={organizerId}")
    public Iterable<Event> getEventsByOrganizer(@RequestParam String organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }
    

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        eventRepository.save(event); 
        return ResponseEntity.ok(event);
    }

}
