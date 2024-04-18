package com.example.greenbay.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.greenbay.models.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    public Project findByTitle(String title);

    public Iterable<Project> findByOwnerId(String ownerId);    

}
