package com.example.assignmnet.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequestDto {
        private String productName;
        private String Discription;
        private Double price;
}
