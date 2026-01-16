package com.firat.service;

import com.firat.dto.request.ProductCreateRequest;
import com.firat.dto.request.ProductUpdateRequest;
import com.firat.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse create(ProductCreateRequest request);
    ProductResponse update(Long id, ProductUpdateRequest request);
    Page<ProductResponse> listPublic(String search, int page, int size);
}
