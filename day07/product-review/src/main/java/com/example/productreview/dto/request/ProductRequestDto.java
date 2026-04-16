package com.example.productreview.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ProductRequestDto {

    @NotBlank(message = "productName is required")
    private String productName;

    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than 0")
    private Double price;
}