package com.example.cropadvisor.repository;

import com.example.cropadvisor.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    List<Farmer> findByRegionId(Long regionId);
}
