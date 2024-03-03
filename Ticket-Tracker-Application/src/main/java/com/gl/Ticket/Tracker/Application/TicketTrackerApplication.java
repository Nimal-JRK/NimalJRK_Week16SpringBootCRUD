package com.gl.Ticket.Tracker.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gl.Ticket.Tracker.Application.model.TicketService;

@SpringBootApplication
public class TicketTrackerApplication {

	@Autowired
	TicketService service;
	public static void main(String[] args) {

		SpringApplication.run(TicketTrackerApplication.class, args);		
	}

}
