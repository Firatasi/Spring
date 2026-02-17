package com.demo.blogapi.articles;

import com.demo.blogapi.articles.dto.*;
import com.demo.blogapi.security.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public PagedResponse<ArticleListItemResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        return articleService.list(page, size, sort);
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse detail(@PathVariable long id) {
        return articleService.detail(id);
    }

    @PostMapping
    public ArticleDetailResponse create(@AuthenticationPrincipal AuthenticatedUser au,
                                        @Valid @RequestBody ArticleCreateUpdateRequest req) {
        return articleService.create(au, req);
    }

    @PutMapping("/{id}")
    public ArticleDetailResponse update(@PathVariable long id,
                                        @AuthenticationPrincipal AuthenticatedUser au,
                                        @Valid @RequestBody ArticleCreateUpdateRequest req) {
        return articleService.update(id, au, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id, @AuthenticationPrincipal AuthenticatedUser au) {
        articleService.delete(id, au);
    }
}

