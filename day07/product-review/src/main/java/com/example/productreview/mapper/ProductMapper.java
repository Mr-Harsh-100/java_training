package com.example.productreview.mapper;

import com.example.productreview.entity.Product;
import com.example.productreview.dto.request.ProductRequestDto;
import com.example.productreview.dto.response.ProductResponseDto;

public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto) {
        if (dto == null) return null;

        Product entity = new Product();
        // TODO: map fields
        return entity;
    }

    public static ProductResponseDto toResponse(Product entity) {
        if (entity == null) return null;

        ProductResponseDto dto = new ProductResponseDto();
        // TODO: map fields
        return dto;
    }
}
