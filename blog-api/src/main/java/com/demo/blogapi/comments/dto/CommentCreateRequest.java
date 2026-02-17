package com.demo.blogapi.comments.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequest(@NotBlank String content) {}

