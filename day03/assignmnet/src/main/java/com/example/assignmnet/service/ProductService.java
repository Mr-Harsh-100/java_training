package com.example.assignmnet.service;

import com.example.assignmnet.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        return Arrays.asList(
                new Product(1L, "Laptop", 75000),
                new Product(2L, "Phone", 25000),
                new Product(3L, "Headphones", 3000)
        );
    }
}