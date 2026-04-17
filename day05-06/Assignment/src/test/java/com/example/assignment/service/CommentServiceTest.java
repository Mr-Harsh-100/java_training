package com.example.assignment.service;

import com.example.assignment.dto.CommentRequest;
import com.example.assignment.model.Comment;
import com.example.assignment.model.Post;
import com.example.assignment.model.User;
import com.example.assignment.repository.CommentRepository;
import com.example.assignment.repository.PostRepository;
import com.example.assignment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    private User user;
    private Post post;
    private Comment comment;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);

        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setPost(post);
        comment.setDepth(0);
    }

    @Test
    void shouldAddComment_whenValidRequest(){
        CommentRequest request = new CommentRequest();
        request.setUserId(1L);
        request.setPostId(1L);
        request.setContent("Test comment");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Comment result = commentService.addComment(request);

        assertNotNull(result);
        assertEquals("Test comment", result.getContent());
        assertEquals(0, result.getDepth());

        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(postRepository, times(1)).save(post);

    }

    @Test
    void shouldAddReply_whenValidRequest(){

        CommentRequest request = new CommentRequest();
        request.setUserId(1L);
        request.setContent("Reply");

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(commentRepository.save(any(Comment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        Comment result = commentService.addReply(1L, request);

        assertNotNull(result);
        assertEquals(1, result.getDepth()); // parent depth 0 → reply 1

        verify(commentRepository, times(2)).save(any(Comment.class));

    }
}
