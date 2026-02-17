package com.demo.blogapi.comments;

import com.demo.blogapi.comments.dto.CommentCreateRequest;
import com.demo.blogapi.comments.dto.CommentResponse;
import com.demo.blogapi.security.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/articles/{articleId}/comments")
    public List<CommentResponse> listByArticle(@PathVariable long articleId) {
        return commentService.listByArticle(articleId);
    }

    @PostMapping("/articles/{articleId}/comments")
    public CommentResponse add(@PathVariable long articleId,
                               @AuthenticationPrincipal AuthenticatedUser au,
                               @Valid @RequestBody CommentCreateRequest req) {
        return commentService.addToArticle(articleId, au, req);
    }

    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable long id,
                       @AuthenticationPrincipal AuthenticatedUser au) {
        commentService.delete(id, au);
    }
}

