package com.example.cropadvisor.controller;

import com.example.cropadvisor.model.Region;
import com.example.cropadvisor.service.RegionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionServiceImpl service;

    public RegionController(RegionServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Region createRegion(@RequestBody Region region) {
        return service.createRegion(region);
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return service.getAllRegions();
    }

    @GetMapping("/{id}")
    public Region getRegion(@PathVariable Long id) {
        return service.getRegion(id);
    }
}