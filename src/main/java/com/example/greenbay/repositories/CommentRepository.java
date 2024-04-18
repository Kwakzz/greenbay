package com.example.greenbay.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.greenbay.models.Comment;


@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    public Iterable<Comment> findByThreadId(String threadId);

}
