package com.example.assignment.controller;

import com.example.assignment.dto.CommentRequest;
import com.example.assignment.model.Comment;
import com.example.assignment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // ✅ Add new comment
    @PostMapping
    public ResponseEntity<Comment> addComment(
            @Valid @RequestBody CommentRequest request
    ) {
        Comment comment = commentService.addComment(request);
        return ResponseEntity.ok(comment);
    }

    // Reply to a comment
    @PostMapping("/{commentId}/reply")
    public ResponseEntity<Comment> addReply(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ) {
        Comment reply = commentService.addReply(commentId, request);
        return ResponseEntity.ok(reply);
    }

    // Get paginated comments (top-level)
    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<Comment>> getComments(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Comment> comments = commentService.getComments(postId, page, size);
        return ResponseEntity.ok(comments);
    }

    // Get ALL comments (use carefully)
    @GetMapping("/post/{postId}/all")
    public ResponseEntity<List<Comment>> getAllComments(
            @PathVariable Long postId
    ) {
        List<Comment> comments = commentService.getAllComments(postId);
        return ResponseEntity.ok(comments);
    }

    // Get paginated replies
    @GetMapping("/{commentId}/replies")
    public ResponseEntity<Page<Comment>> getReplies(
            @PathVariable Long commentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Comment> replies = commentService.getReplies(commentId, page, size);
        return ResponseEntity.ok(replies);
    }

    // Get ALL replies ( small data only)
    @GetMapping("/{commentId}/replies/all")
    public ResponseEntity<List<Comment>> getAllReplies(
            @PathVariable Long commentId
    ) {
        List<Comment> replies = commentService.getAllReplies(commentId);
        return ResponseEntity.ok(replies);
    }
}
