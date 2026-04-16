package com.example.productreview.mapper;

import com.example.productreview.entity.Review;
import com.example.productreview.dto.request.ReviewRequestDto;
import com.example.productreview.dto.response.ReviewResponseDto;

public class ReviewMapper {

    public static Review toEntity(ReviewRequestDto dto) {
        if (dto == null) return null;

        Review entity = new Review();
        // TODO: map fields
        return entity;
    }

    public static ReviewResponseDto toResponse(Review entity) {
        if (entity == null) return null;

        ReviewResponseDto dto = new ReviewResponseDto();
        // TODO: map fields
        return dto;
    }
}
