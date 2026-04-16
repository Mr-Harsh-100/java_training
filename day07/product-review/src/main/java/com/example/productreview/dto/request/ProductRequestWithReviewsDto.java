package com.example.productreview.dto.request;

import com.example.productreview.entity.Product;
import com.example.productreview.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequestWithReviewsDto {

    private ProductRequestDto product;
    private List<Review> reviews;
}