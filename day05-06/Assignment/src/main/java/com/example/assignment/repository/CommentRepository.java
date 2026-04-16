package com.example.assignment.repository;

import com.example.assignment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 🔥 Top-level comments (paginated)
    Page<Comment> findByPostIdAndParentIsNullAndIsDeletedFalse(
            Long postId,
            Pageable pageable
    );

    List<Comment> findByPostIdAndParentIsNullAndIsDeletedFalse(Long postId);

    Page<Comment> findByParentIdAndIsDeletedFalse(Long parentId, Pageable pageable);

    List<Comment> findByParentIdAndIsDeletedFalse(Long parentId);

    // Count replies (optional optimization)
    Long countByParentId(Long parentId);
}
