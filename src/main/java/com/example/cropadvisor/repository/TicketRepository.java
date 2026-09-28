package com.example.cropadvisor.repository;

import com.example.cropadvisor.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByFarmerId(Long farmerId);

    List<Ticket> findByOfficerId(Long officerId);

    List<Ticket> findByRegionId(Long regionId);
}