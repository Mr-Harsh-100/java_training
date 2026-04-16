package com.example.productreview.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.productreview.entity.Review;
import com.example.productreview.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review create(@RequestBody Review entity) {
        return service.create(entity);
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Review> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable Long id, @RequestBody Review entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
