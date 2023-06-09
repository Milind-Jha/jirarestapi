package com.jira.ticketrest.bean;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Ticket {
	
	
	private String ticketId;

	private TicketStatus ticketStatus;
	
	@NotNull(message = "creator cannot be null")
	private final User creator;
	
	private User assignedTo;
	@NotEmpty(message = "content cannot be blank")
	@Size(min = 3,max = 20,message = "content has to be between 3 to 20 characters")
	private String content;
	
	public Ticket(User creator,String content) {
		this.ticketId = UUID.randomUUID().toString();
		this.ticketStatus = TicketStatus.CREATED;
		this.creator = creator;
		this.assignedTo = creator;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTicketId() {
		return ticketId;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus finalTicketStatus) {
		if(!this.ticketStatus.equals(null)&& !finalTicketStatus.equals(null) && finalTicketStatus.equals(TicketStatus.CANCELLED))
			this.ticketStatus = this.ticketStatus.cancelTicket(finalTicketStatus);
		if(this.ticketStatus.equals(null)|| finalTicketStatus.equals(null))
			return;
		if(this.ticketStatus.equals(this.ticketStatus.CREATED) && finalTicketStatus.equals(finalTicketStatus.OPEN))
			this.ticketStatus=this.ticketStatus.getNextStatus(finalTicketStatus.toString());
		if(this.ticketStatus.equals(this.ticketStatus.OPEN) && finalTicketStatus.equals(finalTicketStatus.IN_PROGRESS))
			this.ticketStatus=this.ticketStatus.getNextStatus(finalTicketStatus.toString());
		if(this.ticketStatus.equals(this.ticketStatus.IN_PROGRESS) && finalTicketStatus.equals(finalTicketStatus.DONE))
			this.ticketStatus=this.ticketStatus.getNextStatus(finalTicketStatus.toString());	
		if(this.ticketStatus.equals(this.ticketStatus.DONE) && finalTicketStatus.equals(finalTicketStatus.IN_REVIEW))
			this.ticketStatus=this.ticketStatus.getNextStatus(finalTicketStatus.toString());	
		if(this.ticketStatus.equals(this.ticketStatus.IN_REVIEW) && finalTicketStatus.equals(finalTicketStatus.APPROVED))
			this.ticketStatus=this.ticketStatus.getNextStatus(finalTicketStatus.toString());
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getCreator() {
		return creator;
	}
	
	

	
	
}
