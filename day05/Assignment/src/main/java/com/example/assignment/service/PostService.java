package com.example.assignment.service;

import com.example.assignment.dto.PostRequest;
import com.example.assignment.model.Post;
import com.example.assignment.model.User;
import com.example.assignment.repository.PostRepository;
import com.example.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    public Post createPost(Long userId, PostRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);

        postRepository.save(post);
        return post;
    }

    public Page<Post> getPosts(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return postRepository.findAllByIsDeletedFalse(pageable);
    }
}
