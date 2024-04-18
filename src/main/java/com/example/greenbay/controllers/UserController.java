package com.example.greenbay.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.greenbay.models.User;
import com.example.greenbay.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("?id={id}")
    public User getUser(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user); 
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
