package com.example.greenbay;
import com.example.greenbay.controllers.ProjectController;
import com.example.greenbay.models.Project;
import com.example.greenbay.repositories.ProjectRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProjectById() {
        String projectId = "1";
        Project mockProject = new Project("Test Project", "Description", "2024-12-31", "ownerId");
        mockProject.setId(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(mockProject));

        Project project = projectController.getProject(projectId);

        assertNotNull(project);
        assertEquals(projectId, project.getId());
        assertEquals("Test Project", project.getTitle());
        assertEquals("Description", project.getDescription());
        assertEquals("2024-12-31", project.getDeadline());
        assertEquals("open", project.getStatus());
        assertEquals("ownerId", project.getOwnerId());
    }

    @Test
    void testGetAllProjects() {
        List<Project> mockProjects = new ArrayList<>();
        mockProjects.add(new Project("Project 1", "Description 1", "2024-12-31", "ownerId"));
        mockProjects.add(new Project("Project 2", "Description 2", "2024-12-31", "ownerId"));

        when(projectRepository.findAll()).thenReturn(mockProjects);

        Iterable<Project> projects = projectController.getProjects();

        assertNotNull(projects);
        assertEquals(2, ((List<Project>) projects).size());
    }

    @Test
    void testCreateProject() {
        Project newProject = new Project("New Project", "New Description", "2024-12-31", "ownerId");

        when(projectRepository.save(newProject)).thenReturn(newProject);

        ResponseEntity<Project> responseEntity = projectController.createProject(newProject);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newProject, responseEntity.getBody());
        verify(projectRepository, times(1)).save(newProject);
    }
}
