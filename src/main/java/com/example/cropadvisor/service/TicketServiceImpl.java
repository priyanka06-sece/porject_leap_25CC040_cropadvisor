package com.example.cropadvisor.service;

import com.example.cropadvisor.model.Farmer;
import com.example.cropadvisor.model.Officer;
import com.example.cropadvisor.model.Ticket;
import com.example.cropadvisor.repository.FarmerRepository;
import com.example.cropadvisor.repository.OfficerRepository;
import com.example.cropadvisor.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl {

    private final TicketRepository ticketRepository;
    private final FarmerRepository farmerRepository;
    private final OfficerRepository officerRepository;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            FarmerRepository farmerRepository,
            OfficerRepository officerRepository) {

        this.ticketRepository = ticketRepository;
        this.farmerRepository = farmerRepository;
        this.officerRepository = officerRepository;
    }

    public Ticket createTicket(Ticket ticket) {

        Farmer farmer = farmerRepository.findById(ticket.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        ticket.setRegionId(farmer.getRegionId());

        List<Officer> officers =
                officerRepository.findByRegionId(farmer.getRegionId());

        if (!officers.isEmpty()) {
            ticket.setOfficerId(officers.get(0).getId());
        }

        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setEscalated(false);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public List<Ticket> getTicketsByFarmer(Long farmerId) {
        return ticketRepository.findByFarmerId(farmerId);
    }

    public List<Ticket> getTicketsByOfficer(Long officerId) {
        return ticketRepository.findByOfficerId(officerId);
    }

    public Ticket closeTicket(Long id, String recommendation) {

        Ticket ticket = getTicket(id);

        ticket.setRecommendation(recommendation);
        ticket.setStatus("CLOSED");
        ticket.setResolvedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public Ticket reopenTicket(Long id) {

        Ticket ticket = getTicket(id);

        ticket.setStatus("OPEN");
        ticket.setReopenedAt(LocalDateTime.now());

        // Timer starts again from reopening
        ticket.setCreatedAt(LocalDateTime.now());

        ticket.setResolvedAt(null);
        ticket.setEscalated(false);

        return ticketRepository.save(ticket);
    }

    public Ticket checkEscalation(Long id) {

        Ticket ticket = getTicket(id);

        if (!"CLOSED".equals(ticket.getStatus())) {

            Duration duration =
                    Duration.between(ticket.getCreatedAt(), LocalDateTime.now());

            if (duration.toHours() >= 48) {
                ticket.setEscalated(true);
                ticket.setStatus("ESCALATED");
                ticketRepository.save(ticket);
            }
        }

        return ticket;
    }
}