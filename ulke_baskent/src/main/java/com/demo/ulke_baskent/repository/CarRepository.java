package com.demo.ulke_baskent.repository;

import com.demo.ulke_baskent.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    //modele göre arama
    Optional<Car> findByModel(String model);
}
