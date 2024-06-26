package com.example.greenbay.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.greenbay.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email); 

}
