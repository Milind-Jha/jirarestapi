package com.jira.ticketrest.exception;

public class TicketNotFoundException extends RuntimeException{

	public TicketNotFoundException(String message) {
		super(message);
	}
	
}
