package com.example.greenbay;
import com.example.greenbay.controllers.ThreadController;
import com.example.greenbay.models.Thread;
import com.example.greenbay.repositories.ThreadRepository;

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

class ThreadControllerTest {

    @Mock
    private ThreadRepository threadRepository;

    @InjectMocks
    private ThreadController threadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetThreadById() {
        String threadId = "1";
        Thread mockThread = new Thread("Test Thread", "Description", "authorId");
        mockThread.setId(threadId);

        when(threadRepository.findById(threadId)).thenReturn(Optional.of(mockThread));

        Thread thread = threadController.getThread(threadId);

        assertNotNull(thread);
        assertEquals(threadId, thread.getId());
        assertEquals("Test Thread", thread.getTitle());
        assertEquals("Description", thread.getDescription());
        assertEquals("authorId", thread.getAuthor());
    }

    @Test
    void testGetAllThreads() {
        List<Thread> mockThreads = new ArrayList<>();
        mockThreads.add(new Thread("Thread 1", "Description 1", "authorId"));
        mockThreads.add(new Thread("Thread 2", "Description 2", "authorId"));

        when(threadRepository.findAll()).thenReturn(mockThreads);

        Iterable<Thread> threads = threadController.getThreads();

        assertNotNull(threads);
        assertEquals(2, ((List<Thread>) threads).size());
    }

    @Test
    void testCreateThread() {
        Thread newThread = new Thread("New Thread", "New Description", "authorId");

        when(threadRepository.save(newThread)).thenReturn(newThread);

        ResponseEntity<Thread> responseEntity = threadController.createThread(newThread);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newThread, responseEntity.getBody());
        verify(threadRepository, times(1)).save(newThread);
    }
}
