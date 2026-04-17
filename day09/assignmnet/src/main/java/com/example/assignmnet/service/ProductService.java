package com.example.assignmnet.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.assignmnet.entity.Product;
import com.example.assignmnet.repository.ProductRepository;
import com.example.assignmnet.dto.request.ProductRequestDto;
import com.example.assignmnet.dto.response.ProductResponseDto;
import com.example.assignmnet.mapper.ProductMapper;
import com.example.assignmnet.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDto create(ProductRequestDto requestDto) {
        Product entity = ProductMapper.toEntity(requestDto);
        Product savedEntity = repository.save(entity);
        return ProductMapper.toResponse(savedEntity);
    }

    public ProductResponseDto getById(Long id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toResponse(entity);
    }

    public List<ProductResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDto update(Long id, ProductRequestDto requestDto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        Product updatedEntity = ProductMapper.toEntity(requestDto);
        updatedEntity.setId(existing.getId());
        updatedEntity.setCreatedAt(existing.getCreatedAt());
        
        Product savedEntity = repository.save(updatedEntity);
        return ProductMapper.toResponse(savedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
