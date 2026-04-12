package com.demo.blogapi.articles.dto;

import java.time.Instant;

public record ArticleDetailResponse(
        Long id,
        String title,
        String content,
        String authorEmail,
        Instant createdAt,
        Instant updatedAt
) {}
