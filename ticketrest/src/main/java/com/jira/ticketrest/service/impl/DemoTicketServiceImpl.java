package com.jira.ticketrest.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jira.ticketrest.bean.Ticket;
import com.jira.ticketrest.dao.DemoTicketDao;
import com.jira.ticketrest.dao.DemoUserDao;
import com.jira.ticketrest.service.DemoTicketService;

public class DemoTicketServiceImpl implements DemoTicketService {
	

	private DemoTicketDao demoTicketDao;
	private DemoUserDao demoUserDao;
	
	public DemoTicketServiceImpl(DemoTicketDao demoTicketDao, DemoUserDao demoUserDao) {
		this.demoTicketDao = demoTicketDao;
		this.demoUserDao = demoUserDao;
	}

	@Override
	public List<Ticket> getCreatedTicket(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getAllAssignedTickets(String userId) throws IllegalStateException{
		if (demoUserDao.getUser(userId) != null) {
			if (!demoTicketDao.getAllAssignedTickets(userId).isEmpty()) {
				return demoTicketDao.getAllAssignedTickets(userId);
			}
			return null;
		}
		throw new IllegalStateException("User with user id : " + userId + " not found");
	}

	@Override
	public Ticket getTicketById(String ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Ticket> getAllCreatedAndAssignedTickets(String ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket create(String userId, Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

}
