package com.demo.blogapi.comments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByArticleIdOrderByCreatedAtDesc(Long articleId);
}

