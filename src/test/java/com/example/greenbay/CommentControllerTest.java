package com.example.greenbay;
import com.example.greenbay.controllers.CommentController;
import com.example.greenbay.models.Comment;
import com.example.greenbay.repositories.CommentRepository;

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

class CommentControllerTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentById() {
        String commentId = "1";
        Comment mockComment = new Comment("Test Comment", "authorId", "threadId");
        mockComment.setId(commentId);

        // Mocking repository behavior
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(mockComment));

        // Call the controller method
        Comment comment = commentController.getComment(commentId);

        // Verify the result
        assertNotNull(comment);
        assertEquals(commentId, comment.getId());
        assertEquals("Test Comment", comment.getContent());
        assertEquals("authorId", comment.getAuthor());
        assertEquals("threadId", comment.getThread());
    }

    @Test
    void testGetCommentsByThread() {
        String threadId = "threadId";
        List<Comment> mockComments = new ArrayList<>();
        mockComments.add(new Comment("Comment 1", "authorId", threadId));
        mockComments.add(new Comment("Comment 2", "authorId", threadId));

        when(commentRepository.findByThreadId(threadId)).thenReturn(mockComments);

        Iterable<Comment> comments = commentController.getCommentsByThread(threadId);

        assertNotNull(comments);
        assertEquals(2, ((List<Comment>) comments).size());
    }

    @Test
    void testCreateComment() {
        Comment newComment = new Comment("New Comment", "authorId", "threadId");

        when(commentRepository.save(newComment)).thenReturn(newComment);

        ResponseEntity<Comment> responseEntity = commentController.createComment(newComment);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newComment, responseEntity.getBody());
        verify(commentRepository, times(1)).save(newComment);
    }

    @Test
    void testReplyToComment() {
        String commentId = "1";
        Comment mockComment = new Comment("Parent Comment", "authorId", "threadId");
        mockComment.setId(commentId);
        Comment reply = new Comment("Reply", "authorId", "threadId");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(mockComment));
        when(commentRepository.save(mockComment)).thenReturn(mockComment);

        ResponseEntity<Comment> responseEntity = commentController.replyToComment(commentId, reply);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reply, responseEntity.getBody());
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).save(mockComment);
    }
}
