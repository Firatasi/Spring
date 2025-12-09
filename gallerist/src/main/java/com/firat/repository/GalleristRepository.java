package com.firat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firat.model.Gallerist;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long>{

}
