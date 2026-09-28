package com.example.cropadvisor.controller;

import com.example.cropadvisor.model.Farmer;
import com.example.cropadvisor.service.FarmerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerServiceImpl service;

    public FarmerController(FarmerServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Farmer createFarmer(@RequestBody Farmer farmer) {
        return service.createFarmer(farmer);
    }

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return service.getAllFarmers();
    }

    @GetMapping("/{id}")
    public Farmer getFarmer(@PathVariable Long id) {
        return service.getFarmer(id);
    }

    @GetMapping("/region/{regionId}")
    public List<Farmer> getFarmersByRegion(
            @PathVariable Long regionId) {

        return service.getFarmersByRegion(regionId);
    }
}