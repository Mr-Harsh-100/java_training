package com.example.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull(message = "Post ID is required")
    private Long postId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Comment cannot be empty")
    @Size(min = 1, max = 1000, message = "Comment must be 1-1000 characters")
    private String content;
}

