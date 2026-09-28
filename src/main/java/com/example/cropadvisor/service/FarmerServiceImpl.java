package com.example.cropadvisor.service;

import com.example.cropadvisor.model.Farmer;
import com.example.cropadvisor.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerServiceImpl {

    private final FarmerRepository repository;

    public FarmerServiceImpl(FarmerRepository repository) {
        this.repository = repository;
    }

    public Farmer createFarmer(Farmer farmer) {
        return repository.save(farmer);
    }

    public List<Farmer> getAllFarmers() {
        return repository.findAll();
    }

    public Farmer getFarmer(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public List<Farmer> getFarmersByRegion(Long regionId) {
        return repository.findByRegionId(regionId);
    }
}