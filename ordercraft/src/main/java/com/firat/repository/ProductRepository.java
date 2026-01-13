package com.firat.repository;

import com.firat.entity.Product;
import com.firat.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByUsername(String sku);
    Page<Product> findByActiveTrue(Pageable pageable);
    Page<Product> findByActiveTrue(String name, Pageable pageable);
}
