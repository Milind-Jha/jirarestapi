package com.jira.ticketrest.service;

import java.util.List;
import java.util.Set;

import com.jira.ticketrest.bean.Ticket;

public interface DemoTicketService {
	
	public List<Ticket> getCreatedTicket(String userId);
	public List<Ticket> getAllAssignedTickets(String userId);
	public Ticket getTicketById(String ticketId);
	public Set<Ticket> getAllCreatedAndAssignedTickets(String ticketId);
	public Ticket create(String userId,Ticket ticket);
	
}
