package com.example.productreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productreview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
