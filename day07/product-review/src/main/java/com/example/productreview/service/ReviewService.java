package com.example.productreview.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.productreview.entity.Review;
import com.example.productreview.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review create(Review entity) {
        return repository.save(entity);
    }

    public Review getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<Review> getAll() {
        return repository.findAll();
    }

    public Review update(Long id, Review entity) {
        Review existing = getById(id);
        // TODO: update fields
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
