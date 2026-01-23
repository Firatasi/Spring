package com.demo.yemekmasasi.repository;

import com.demo.yemekmasasi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
