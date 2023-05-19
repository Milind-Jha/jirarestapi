package com.jira.ticketrest.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jira.ticketrest.bean.Ticket;

@Repository
public class DemoTicketDao {
	

	private static DemoUserDao demoUserDao = new DemoUserDao();
	
	private static List<Ticket> tickets = new ArrayList();
	
	static {
		
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(0)),"Bugs in service layer"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(1)),"Security Bug"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(2)),"User unable to login"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(0)),"JWT token not working properly"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(1)),"Unable to send automated email"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(2)),"user not found in database"));
		tickets.add(new Ticket(demoUserDao.getUser(demoUserDao.getUserIds().get(2)),"unable to communicate with external payment services"));
		
	}
	
	
	public List<Ticket> getAllCreatedTickets(String userId){
		List<Ticket> ans = new LinkedList();
		for(Ticket ticket: tickets) {
			if(ticket.getCreator().equals(demoUserDao.getUser(userId)))
				ans.add(ticket);
		}
		return ans;
	}
	
	public Ticket getTicket(String ticketId) {
		for(Ticket ticket:tickets) {
			if(ticket.getTicketId().equals(ticketId))
				return ticket;
		}
		return null;
	}
	
	public List<Ticket> getAllAssignedTickets(String userIds){
		List<Ticket> ans = new LinkedList();
		for(Ticket ticket: tickets) {
			if(ticket.getAssignedTo().equals(demoUserDao.getUser(userIds)))
				ans.add(ticket);
		}
		return ans;
	}
	
	public Set<Ticket> getAllCreatedAndAssignedTickets(String userIds){
		Set<Ticket> collect1 = getAllCreatedTickets(userIds).stream().collect(Collectors.toSet());
		Set<Ticket> collect2 = getAllAssignedTickets(userIds).stream().collect(Collectors.toSet());
		Set<Ticket> ans = new HashSet<Ticket>() {
			{
				addAll(collect1);
				addAll(collect2);
			}
		};
		return ans;
	}
	
	public Ticket createTicket(String userId,Ticket ticket) {
		
		Ticket newTicket = new Ticket(demoUserDao.getUser(userId), ticket.getContent());
		tickets.add(newTicket);
		return newTicket;
	}
	
	
	
}
