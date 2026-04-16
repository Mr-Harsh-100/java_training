package com.example.assignment.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReplyRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Reply cannot be empty")
    @Size(min = 1, max = 1000)
    private String content;
}