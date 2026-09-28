package com.example.cropadvisor.controller;

import com.example.cropadvisor.model.Officer;
import com.example.cropadvisor.service.OfficerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/officers")
public class OfficerController {

    private final OfficerServiceImpl service;

    public OfficerController(OfficerServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Officer createOfficer(@RequestBody Officer officer) {
        return service.createOfficer(officer);
    }

    @GetMapping
    public List<Officer> getAllOfficers() {
        return service.getAllOfficers();
    }

    @GetMapping("/{id}")
    public Officer getOfficer(@PathVariable Long id) {
        return service.getOfficer(id);
    }

    @GetMapping("/region/{regionId}")
    public List<Officer> getOfficersByRegion(
            @PathVariable Long regionId) {

        return service.getOfficersByRegion(regionId);
    }
}