package com.example.assignmnet.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {
    private Long id;
        private String productName;
        private String Discription;
        private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
