package com.firat.service.impl;

import com.firat.dto.request.ProductCreateRequest;
import com.firat.dto.request.ProductUpdateRequest;
import com.firat.dto.response.ProductResponse;
import com.firat.entity.Product;
import com.firat.exception.DuplicateResourceException;
import com.firat.exception.NotFoundException;
import com.firat.mapper.ProductMapper;
import com.firat.repository.ProductRepository;
import com.firat.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("SKU already exists: " + request.getSku());
        }
        Product saved = productRepository.save(ProductMapper.toEntity(request));
        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse update(Long id, ProductUpdateRequest req) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found: " + id));

        if (req.getSku() != null && !req.getSku().equals(p.getSku())
                && productRepository.existsBySku(req.getSku())) {
            throw new DuplicateResourceException("SKU already exists: " + req.getSku());
        }

        if (req.getName() != null) p.setName(req.getName());
        if (req.getSku() != null) p.setSku(req.getSku());
        if (req.getPrice() != null) p.setPrice(req.getPrice());
        if (req.getStock() != null) p.setStock(req.getStock());
        if (req.getActive() != null) p.setActive(req.getActive());

        Product saved = productRepository.save(p);
        return ProductMapper.toResponse(saved);
    }

    @Override
    public Page<ProductResponse> listPublic(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Product> result;
        if (search == null || search.isBlank()) {
            result = productRepository.findByActiveTrue(pageable);
        } else {
            result = productRepository.findByActiveTrueAndNameContainingIgnoreCase(search.trim(), pageable);
        }

        return result.map(ProductMapper::toResponse);
    }
}
