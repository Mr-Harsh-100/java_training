package com.example.assignmnet.mapper;

import com.example.assignmnet.entity.Product;
import com.example.assignmnet.dto.request.ProductRequestDto;
import com.example.assignmnet.dto.response.ProductResponseDto;

public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto) {
        if (dto == null) return null;
        
        Product entity = new Product();
        entity.setProductName(dto.getProductName());
        entity.setDiscription(dto.getDiscription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static ProductResponseDto toResponse(Product entity) {
        if (entity == null) return null;
        
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductName(entity.getProductName());
        dto.setDiscription(entity.getDiscription());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
