package com.example.greenbay.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.greenbay.models.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    public Event findByTitle(String title);    
    public Event findByDate(String date);
    public Event findByLocation(String location);
    public Iterable<Event> findByOrganizerId(String organizerId);

}
