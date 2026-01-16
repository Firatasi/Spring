package com.firat.repository;

import com.firat.entity.Product;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByUsername(String sku);
    Page<Product> findByActiveTrue(Pageable pageable);
    Page<Product> findByActiveTrue(String name, Pageable pageable);

    boolean existsBySku(@Size(max=50) String sku);

    Page<Product> findByActiveTrueAndNameContainingIgnoreCase(String trim, Pageable pageable);
}
