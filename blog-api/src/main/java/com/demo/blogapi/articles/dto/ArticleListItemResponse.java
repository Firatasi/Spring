package com.demo.blogapi.articles.dto;

import java.time.Instant;

public record ArticleListItemResponse(
        Long id,
        String title,
        String authorEmail,
        Instant createdAt
) {}

