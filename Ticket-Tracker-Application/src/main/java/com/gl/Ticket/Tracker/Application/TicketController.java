package com.gl.Ticket.Tracker.Application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.Ticket.Tracker.Application.model.Ticket;
import com.gl.Ticket.Tracker.Application.model.TicketService;

@Controller
public class TicketController {
	@Autowired
	TicketService service;

	@RequestMapping("/")
	public String showTicket(Model data) {
		List<Ticket> tickets=service.getAllTicket();
		data.addAttribute("ticket",tickets);
		return "showTickets";

	}

	@RequestMapping("/addTicket")
	public String addForm() {
		return "addTicket";
	}

	@PostMapping("/insertion")
	public String insertTicket(@RequestParam String ticketTitle,@RequestParam String ticketShortDescription,@RequestParam String content,Model  data) {
		LocalDate ticketBookedDate1=LocalDate.now();
		Ticket t1=  Ticket .builder().ticketTitle(ticketTitle).ticketShortDescription(ticketShortDescription).ticketBookedDate(ticketBookedDate1).content(content).build();

		service.addTicket(t1);

		List<Ticket> tickets=service.getAllTicket();
		data.addAttribute("ticket",tickets);
		return "showTickets";

	}

	@GetMapping("/deleteTicket")
	public String deleteTicket(@RequestParam int id,Model data) {
		Ticket t1= new Ticket(id, null, null, null, null);
		service.delete(t1);

		List<Ticket> tickets=service.getAllTicket();
		data.addAttribute("ticket",tickets);
		return "showTickets";
	}





	@GetMapping("/editTicket")
	public String updateTicketForm(@RequestParam int id,Model data) {

		Ticket t1 =service.getById(id);
		data.addAttribute("tickets",t1);
		return "updateForm";

	}

	@PostMapping("/update")
	public String update(@RequestParam int id,@RequestParam String ticketTitle,@RequestParam String ticketShortDescription,@RequestParam LocalDate ticketBookedDate,@RequestParam String content,Model data) {
		Ticket t1= new Ticket(id, ticketTitle, ticketShortDescription, ticketBookedDate, content);
		service.updateTicket(t1);

		List<Ticket> tickets=service.getAllTicket();
		data.addAttribute("ticket",tickets);
		return "showTickets";


	}

	@GetMapping("/View")
	public String viewTicket(@RequestParam int id,Model data) {

		Ticket t1 =service.getById(id);
		data.addAttribute("tickets",t1);
		return "viewForm";

	}

	@GetMapping("/searchTicket")
	public String searchTicket(@RequestParam String ticketTitle ,Model data) {
		List<Ticket> t1= service.searchByName(ticketTitle);
		data.addAttribute("ticket",t1);
		return "showTickets";


	}




}
