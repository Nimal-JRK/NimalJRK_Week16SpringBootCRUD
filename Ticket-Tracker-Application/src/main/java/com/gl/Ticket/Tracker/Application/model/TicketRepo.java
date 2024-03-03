package com.gl.Ticket.Tracker.Application.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{

}
