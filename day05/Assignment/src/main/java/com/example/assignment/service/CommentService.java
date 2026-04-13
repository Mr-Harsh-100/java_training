package com.example.assignment.service;

import com.example.assignment.dto.CommentRequest;
import com.example.assignment.model.Comment;
import com.example.assignment.model.Post;
import com.example.assignment.model.User;
import com.example.assignment.repository.CommentRepository;
import com.example.assignment.repository.PostRepository;
import com.example.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Add comment
    public Comment addComment(CommentRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(request.getContent());
        comment.setDepth(0);

        commentRepository.save(comment);

        // increment counter
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return comment;
    }

    // 🔁 Reply to comment
    public Comment addReply(Long parentId, CommentRequest request) {

        Comment parent = commentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment reply = new Comment();
        reply.setParent(parent);
        reply.setPost(parent.getPost());
        reply.setUser(user);
        reply.setContent(request.getContent());
        reply.setDepth(parent.getDepth() + 1);

        commentRepository.save(reply);

        // increment reply count
        parent.setReplyCount(parent.getReplyCount() + 1);
        commentRepository.save(parent);

        return reply;
    }

    // Get comments (paginated)
    public Page<Comment> getComments(Long postId, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return commentRepository
                .findByPostIdAndParentIsNullAndIsDeletedFalse(postId, pageable);
    }

    public List<Comment> getAllComments(Long postId) {
        return commentRepository
                .findByPostIdAndParentIsNullAndIsDeletedFalse(postId);
    }

    public Page<Comment> getReplies(Long commentId, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return commentRepository
                .findByParentIdAndIsDeletedFalse(commentId, pageable);
    }

    // Get replies
    public List<Comment> getAllReplies(Long commentId) {

        return commentRepository
                .findByParentIdAndIsDeletedFalse(commentId);
    }


}
