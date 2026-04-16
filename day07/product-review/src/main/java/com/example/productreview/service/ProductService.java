package com.example.productreview.service;

import java.util.List;

import com.example.productreview.entity.Review;
import com.example.productreview.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.productreview.entity.Product;
import com.example.productreview.repository.ProductRepository;



import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository,
                          ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // 🔥 TRANSACTION METHOD
    @Transactional
    public void createProductWithReviews(Product product, List<Review> reviews) {

        Product savedProduct = productRepository.save(product);

        // ❌ Failure condition 1
        if (reviews == null || reviews.isEmpty()) {
            throw new RuntimeException("Reviews list cannot be empty");
        }

        for (Review review : reviews) {

            // ❌ Failure condition 2 (mid-transaction)
            if (review.getReviewText() == null) {
                throw new RuntimeException("Review text cannot be null");
            }

            review.setProduct(savedProduct);
            reviewRepository.save(review);
        }
    }
}