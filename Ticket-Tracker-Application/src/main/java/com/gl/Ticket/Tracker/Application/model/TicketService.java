package com.gl.Ticket.Tracker.Application.model;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;




@Service
public class TicketService {

	@Autowired
	TicketRepo repo;

	public void addTicket(Ticket ticket) {
		repo.save(ticket);
	}
	public List<Ticket> getAllTicket(){
		return repo.findAll();
	}

	public Ticket getById(int id) {

		if(repo.findById(id).isEmpty()) {
			return null;
		}
		else {
			return repo.findById(id).get();
		}

	}
	public void delete(Ticket dt) {
		repo.delete(dt);
	}

	public List<Ticket> searchByName(String searchKey){
		//1.create  a dummy object based on the search key
		Ticket dummy= new Ticket();

		dummy.setTicketTitle(searchKey);

		//2.Create example with jpa
		ExampleMatcher em= ExampleMatcher.matching().withMatcher("ticketTitle", ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("id","ticketShortDescription","ticketBookedDate","content");

		//3.Combining dummy with where
		Example<Ticket> ex= Example.of(dummy, em);
		return repo.findAll(ex);

	}
	public void updateTicket(Ticket ticket) {
		repo.save(ticket);
	}

}
