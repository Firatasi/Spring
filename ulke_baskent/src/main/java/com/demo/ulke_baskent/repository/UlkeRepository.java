package com.demo.ulke_baskent.repository;

import com.demo.ulke_baskent.entity.Baskent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UlkeRepository extends JpaRepository<Baskent,Long> {
}
