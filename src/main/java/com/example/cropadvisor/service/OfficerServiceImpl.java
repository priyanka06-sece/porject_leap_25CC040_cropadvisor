package com.example.cropadvisor.service;

import com.example.cropadvisor.model.Officer;
import com.example.cropadvisor.repository.OfficerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerServiceImpl {

    private final OfficerRepository repository;

    public OfficerServiceImpl(OfficerRepository repository) {
        this.repository = repository;
    }

    public Officer createOfficer(Officer officer) {
        return repository.save(officer);
    }

    public List<Officer> getAllOfficers() {
        return repository.findAll();
    }

    public Officer getOfficer(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
    }

    public List<Officer> getOfficersByRegion(Long regionId) {
        return repository.findByRegionId(regionId);
    }
}