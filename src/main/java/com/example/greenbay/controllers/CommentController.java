package com.example.greenbay.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.greenbay.models.Comment;
import com.example.greenbay.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("?id={id}")
    public Comment getComment(@PathVariable String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @GetMapping("?threadId={threadId}")
    public Iterable<Comment> getCommentsByThread(@PathVariable String threadId) {
        return commentRepository.findByThreadId(threadId);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        commentRepository.save(comment); 
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> replyToComment(@PathVariable String id, @RequestBody Comment reply) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        comment.getReplies().add(reply);
        commentRepository.save(comment);
        return ResponseEntity.ok(reply);
    }

}