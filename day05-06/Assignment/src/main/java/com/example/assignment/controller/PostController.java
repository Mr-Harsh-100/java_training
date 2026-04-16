package com.example.assignment.controller;

import com.example.assignment.dto.PostRequest;
import com.example.assignment.model.Post;
import com.example.assignment.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // ✅ Create Post
    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestParam Long userId,
            @Valid @RequestBody PostRequest request
    ) {
        Post post = postService.createPost(userId, request);
        return ResponseEntity.ok(post);
    }

    // 📄 Get paginated posts
    @GetMapping
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Post> posts = postService.getPosts(page, size);
        return ResponseEntity.ok(posts);
    }
}
