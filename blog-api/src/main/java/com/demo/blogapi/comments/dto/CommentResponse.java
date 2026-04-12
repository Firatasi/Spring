package com.demo.blogapi.comments.dto;

import java.time.Instant;

public record CommentResponse(
        Long id,
        String content,
        String userEmail,
        Instant createdAt
) {}

