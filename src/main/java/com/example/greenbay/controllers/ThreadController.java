package com.example.greenbay.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.greenbay.models.Thread;
import com.example.greenbay.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/thread")
@CrossOrigin(origins = "*")
public class ThreadController {

    @Autowired
    private ThreadRepository threadRepository;

    @GetMapping("?id={id}")
    public Thread getThread(@PathVariable String id) {
        return threadRepository.findById(id).orElse(null);
    }

    @GetMapping("")
    public Iterable<Thread> getThreads() {
        return threadRepository.findAll();
    }

    @GetMapping("?authorId={authorId}")
    public Iterable<Thread> getThreadsByAuthor(@RequestParam String authorId) {
        return threadRepository.findByAuthorId(authorId);
    }


    @PostMapping
    public ResponseEntity<Thread> createThread(@RequestBody Thread thread) {
        threadRepository.save(thread); 
        return ResponseEntity.ok(thread);
    }

}
