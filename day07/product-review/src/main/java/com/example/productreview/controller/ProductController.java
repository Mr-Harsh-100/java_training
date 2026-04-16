package com.example.productreview.controller;

import java.util.List;

import com.example.productreview.dto.request.ProductRequestDto;
import com.example.productreview.dto.request.ProductRequestWithReviewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.productreview.entity.Product;
import com.example.productreview.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔥 Transaction API
    @PostMapping("/with-reviews")
    public ResponseEntity<String> createProductWithReviews(
            @RequestBody ProductRequestWithReviewsDto request) {

        ProductRequestDto dto = request.getProduct();

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());

        service.createProductWithReviews(product, request.getReviews());

        return ResponseEntity.ok("Saved successfully");
    }
}