package com.jira.ticketrest.exception;

public class IllegalTicketStatusChangeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalTicketStatusChangeException(String message) {
		super(message);
	}

	public IllegalTicketStatusChangeException() {
		super("Illegal status change");
		// TODO Auto-generated constructor stub
	}
}
