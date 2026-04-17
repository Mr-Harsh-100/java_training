package com.example.assignmnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignmnet.entity.Product;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
