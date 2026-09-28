package com.example.cropadvisor.controller;

import com.example.cropadvisor.model.Ticket;
import com.example.cropadvisor.service.TicketServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketServiceImpl service;

    public TicketController(TicketServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return service.createTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return service.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return service.getTicket(id);
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Ticket> getTicketsByFarmer(
            @PathVariable Long farmerId) {

        return service.getTicketsByFarmer(farmerId);
    }

    @GetMapping("/officer/{officerId}")
    public List<Ticket> getTicketsByOfficer(
            @PathVariable Long officerId) {

        return service.getTicketsByOfficer(officerId);
    }

    @PutMapping("/{id}/close")
    public Ticket closeTicket(
            @PathVariable Long id,
            @RequestParam String recommendation) {

        return service.closeTicket(id, recommendation);
    }

    @PutMapping("/{id}/reopen")
    public Ticket reopenTicket(@PathVariable Long id) {
        return service.reopenTicket(id);
    }

    @PutMapping("/{id}/escalate")
    public Ticket escalateTicket(@PathVariable Long id) {
        return service.checkEscalation(id);
    }
}