package com.example.greenbay.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.greenbay.models.Thread;

@Repository
public interface ThreadRepository extends MongoRepository<Thread, String> {

    public Thread findByTitle(String title);

    public Iterable<Thread> findByAuthorId(String authorId);    

}
