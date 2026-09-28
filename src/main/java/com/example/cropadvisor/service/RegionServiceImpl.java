package com.example.cropadvisor.service;

import com.example.cropadvisor.model.Region;
import com.example.cropadvisor.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl {

    private final RegionRepository repository;

    public RegionServiceImpl(RegionRepository repository) {
        this.repository = repository;
    }

    public Region createRegion(Region region) {
        return repository.save(region);
    }

    public List<Region> getAllRegions() {
        return repository.findAll();
    }

    public Region getRegion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found"));
    }
}