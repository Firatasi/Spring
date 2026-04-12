package com.demo.blogapi.articles;

import com.demo.blogapi.articles.dto.*;
import com.demo.blogapi.exception.ApiException;
import com.demo.blogapi.security.AuthenticatedUser;
import com.demo.blogapi.users.Role;
import com.demo.blogapi.users.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public PagedResponse<ArticleListItemResponse> list(int page, int size, String sort) {
        // sort örn: createdAt,desc
        String[] parts = sort.split(",");
        String field = parts.length > 0 ? parts[0] : "createdAt";
        Sort.Direction dir = (parts.length > 1 && "asc".equalsIgnoreCase(parts[1])) ? Sort.Direction.ASC : Sort.Direction.DESC;

        var pageable = PageRequest.of(page, size, Sort.by(dir, field));
        var p = articleRepository.findAll(pageable);

        var items = p.getContent().stream()
                .map(a -> new ArticleListItemResponse(a.getId(), a.getTitle(), a.getAuthor().getEmail(), a.getCreatedAt()))
                .toList();

        return new PagedResponse<>(items, page, size, p.getTotalElements());
    }

    @Cacheable(value = "articles", key = "#id") //ikinci istekte kod bloğunun hepsi çalışmaz cachteki bilgiler tekrar döndürülür
    public ArticleDetailResponse detail(long id) {
        var a = articleRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Article not found"));
        return new ArticleDetailResponse(a.getId(), a.getTitle(), a.getContent(), a.getAuthor().getEmail(), a.getCreatedAt(), a.getUpdatedAt());
    }

    public ArticleDetailResponse create(AuthenticatedUser au, ArticleCreateUpdateRequest req) {
        var author = userRepository.findById(au.id())
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "User not found"));

        Instant now = Instant.now();
        var a = ArticleEntity.builder()
                .title(req.title().trim())
                .content(req.content())
                .author(author)
                .createdAt(now)
                .updatedAt(now)
                .build();

        articleRepository.save(a);
        return detail(a.getId());
    }

    @CacheEvict(value = "articles", allEntries = true) //cachteki bütün articlearı temizle cacheput ile kullanılır
    @CachePut(value = "articles", key = "#id")//sadece verilen idyi günceller
    public ArticleDetailResponse update(long id, AuthenticatedUser au, ArticleCreateUpdateRequest req) {
        var a = articleRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Article not found"));

        ensureOwnerOrAdmin(a, au);

        a.setTitle(req.title().trim());
        a.setContent(req.content());
        a.setUpdatedAt(Instant.now());
        articleRepository.save(a);

        return detail(a.getId());
    }

    public void delete(long id, AuthenticatedUser au) {
        var a = articleRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Article not found"));

        ensureOwnerOrAdmin(a, au);
        articleRepository.delete(a);
    }

    private void ensureOwnerOrAdmin(ArticleEntity a, AuthenticatedUser au) {
        boolean isOwner = a.getAuthor().getId().equals(au.id());
        boolean isAdmin = au.role() == Role.ADMIN;
        if (!isOwner && !isAdmin) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Not allowed");
        }
    }
}

