package com.example.cropadvisor.repository;

import com.example.cropadvisor.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}