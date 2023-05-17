package com.jira.ticketrest.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jira.ticketrest.bean.Ticket;
import com.jira.ticketrest.bean.TicketStatus;
import com.jira.ticketrest.dao.DemoTicketDao;
import com.jira.ticketrest.dao.DemoUserDao;
import com.jira.ticketrest.exception.InvalidUserException;
import com.jira.ticketrest.exception.TicketNotFoundException;
import com.jira.ticketrest.exception.UnauthorisedStatusChangeException;

@RestController
public class TicketController {
	
	private DemoTicketDao demoTicketDao;
	private DemoUserDao demoUserDao;
	
	
	public TicketController(DemoTicketDao demoTicketDao,DemoUserDao demoUserDao) {
		this.demoTicketDao=demoTicketDao;
		this.demoUserDao = demoUserDao;
	}
	
	@GetMapping("users/assignedticket/{userId}")
	public List<Ticket> getAssignedTickets(@PathVariable String userId) {
		return demoTicketDao.getAllAssignedTickets(userId);
	}
	@GetMapping("users/createdticket/{userId}")
	public List<Ticket> getCreatedTickets(@PathVariable String userId) {
		return demoTicketDao.getAllCreatedTickets(userId);
	}
	@GetMapping("users/allticket/{userId}")
	public Set<Ticket> getAllTickets(@PathVariable String userId) {
		return demoTicketDao.getAllCreatedAndAssignedTickets(userId);
	}
	
	@PostMapping("users/createTicket/{userId}")
	public ResponseEntity<Ticket> createTicket(@PathVariable String userId,@RequestBody Ticket ticket) throws IllegalStateException{
		if(demoUserDao.getUser(userId)!=null){
				if(ticket.getCreator()!=null && ticket.getCreator().getUserId()!=null && demoUserDao.getUser(ticket.getCreator().getUserId())!=null){
					if (ticket.getCreator().getUserId().equals(userId)){
						if(ticket.getCreator().getPassword().equals(demoUserDao.getUser(userId).getPassword()) &&
							ticket.getCreator().getName().equalsIgnoreCase(demoUserDao.getUser(userId).getName()) &&
							ticket.getCreator().getEmail().equalsIgnoreCase(demoUserDao.getUser(userId).getEmail())){
						return  ResponseEntity.status(HttpStatus.CREATED).body(demoTicketDao.createTicket(userId, ticket));
					}
					throw new IllegalStateException("Invalid Credentials");
				}
				throw new IllegalStateException("userId : "+ userId+ " does not match with userId given in Ticket :"+ticket.getCreator().getUserId());
			}
			if(ticket==null || ticket.getCreator()==null)
				throw new IllegalStateException("Invalid Credentials");
			throw new IllegalStateException("user with userId : "+ ticket.getCreator().getUserId()+ " does not exist");
		}
		throw new IllegalStateException("User with user id: "+userId+" not found");
	}
	
	@PatchMapping("/users/updatestatus/{userId}/{ticketId}")
	public ResponseEntity<Ticket> updateStatus(@PathVariable String userId,@PathVariable String ticketId,@RequestBody String finalStatus)
			throws UnauthorisedStatusChangeException,IllegalStateException,TicketNotFoundException {
		if(demoUserDao.getUser(userId)!= null) {
//			System.err.println("User not found");
//			return null;
			if(demoTicketDao.getTicket(ticketId)==null)
				throw new TicketNotFoundException("Ticket with "+ ticketId+" has not been found");
			if(demoTicketDao.getAllAssignedTickets(userId).contains(demoTicketDao.getTicket(ticketId))) {
				TicketStatus ticketStatus = demoTicketDao.getTicket(ticketId).getTicketStatus();
				demoTicketDao.getTicket(ticketId).setTicketStatus(ticketStatus.getNextStatus(finalStatus.substring(1,finalStatus.length()-1)));
				return ResponseEntity.status(HttpStatus.OK).body( demoTicketDao.getTicket(ticketId));

			}
			throw new UnauthorisedStatusChangeException("You do not have permission to change the status of ticket with ID : "+ticketId);
		}
		throw new IllegalStateException("User with user id: "+userId+" not found");
	}
	
	@PatchMapping("/users/cancelticket/{userId}/{ticketId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable String userId,@PathVariable String ticketId) throws Exception {
		if(demoUserDao.getUser(userId)== null) {
			System.err.println("User not found");
			return null;
		}
		if(demoTicketDao.getTicket(ticketId)==null)
			throw new TicketNotFoundException("Ticket with "+ ticketId+" has not been found");
		if(demoTicketDao.getAllAssignedTickets(userId).contains(demoTicketDao.getTicket(ticketId))) {
			TicketStatus ticketStatus = demoTicketDao.getTicket(ticketId).getTicketStatus();
			demoTicketDao.getTicket(ticketId).setTicketStatus(this.demoTicketDao.getTicket(ticketId).getTicketStatus().cancelTicket(ticketStatus));
			return ResponseEntity.status(HttpStatus.OK).body(demoTicketDao.getTicket(ticketId));
			
		}
		throw new UnauthorisedStatusChangeException("You do not have permission to cancel the status of ticket with ID : "+ticketId);
	}
	
	
	
	@PatchMapping("/users/updateassigned/{userId}/{newUserId}/{ticketId}")
	public ResponseEntity<Ticket> updateAssignedTo(@PathVariable String userId,@PathVariable String newUserId,@PathVariable String ticketId) throws Exception {
		if(demoUserDao.getUser(userId)== null) {
			System.err.println("User not found");
			return null;
		}
		if(demoUserDao.getUser(newUserId)==null) {
			System.err.println("New user not found");
			return null;
		}
		if(demoTicketDao.getTicket(ticketId)==null)
			throw new TicketNotFoundException("Ticket with "+ ticketId+" has not been found");
		if(demoTicketDao.getAllAssignedTickets(userId).contains(demoTicketDao.getTicket(ticketId))) {
			demoTicketDao.getTicket(ticketId).setAssignedTo(demoUserDao.getUser(newUserId));
			return ResponseEntity.status(HttpStatus.OK).body( demoTicketDao.getTicket(ticketId));
		}
		throw new UnauthorisedStatusChangeException("You do not have permission to change the assigned user to the ticket ID : "+ticketId);
	}
	
	
	
}
