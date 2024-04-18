package com.example.greenbay.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.greenbay.models.Project;
import com.example.greenbay.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("?id={id}")
    public Project getProject(@PathVariable String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @GetMapping("?ownerId={ownerId}")
    public Iterable<Project> getProjectsByOwner(@PathVariable String ownerId) {
        return projectRepository.findByOwnerId(ownerId);
    }
    

    @GetMapping("")
    public Iterable<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        projectRepository.save(project); 
        return ResponseEntity.ok(project);
    }

}
