package com.example.cropadvisor.repository;

import com.example.cropadvisor.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

    List<Officer> findByRegionId(Long regionId);
}