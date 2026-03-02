package com.demo.carapi.repository;

import com.demo.carapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRespository extends JpaRepository<Car,Long> {
}
