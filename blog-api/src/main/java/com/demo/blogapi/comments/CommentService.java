package com.demo.blogapi.comments;

import com.demo.blogapi.articles.ArticleRepository;
import com.demo.blogapi.exception.ApiException;
import com.demo.blogapi.comments.dto.CommentCreateRequest;
import com.demo.blogapi.comments.dto.CommentResponse;
import com.demo.blogapi.security.AuthenticatedUser;
import com.demo.blogapi.users.Role;
import com.demo.blogapi.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          ArticleRepository articleRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public List<CommentResponse> listByArticle(long articleId) {
         articleRepository.findById(articleId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Article not found"));

        return commentRepository.findByArticleIdOrderByCreatedAtDesc(articleId).stream()
                .map(c -> new CommentResponse(c.getId(), c.getContent(), c.getUser().getEmail(), c.getCreatedAt()))
                .toList();
    }

    public CommentResponse addToArticle(long articleId, AuthenticatedUser au, CommentCreateRequest req) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Article not found"));

        var user = userRepository.findById(au.id())
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "User not found"));

        var c = CommentEntity.builder()
                .article(article)
                .user(user)
                .content(req.content())
                .createdAt(Instant.now())
                .build();

        commentRepository.save(c);

        return new CommentResponse(c.getId(), c.getContent(), user.getEmail(), c.getCreatedAt());
    }

    public void delete(long commentId, AuthenticatedUser au) {
        var c = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Comment not found"));

        boolean isOwner = c.getUser().getId().equals(au.id());
        boolean isAdmin = au.role() == Role.ADMIN;

        if (!isOwner && !isAdmin) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Not allowed");
        }

        commentRepository.delete(c);
    }
}
