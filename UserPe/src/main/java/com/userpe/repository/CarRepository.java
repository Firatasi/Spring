package com.userpe.repository;

import com.userpe.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByModel(String name);
}
