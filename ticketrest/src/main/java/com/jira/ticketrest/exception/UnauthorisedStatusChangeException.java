package com.jira.ticketrest.exception;

public class UnauthorisedStatusChangeException extends RuntimeException {

	public UnauthorisedStatusChangeException() {
		super();
	}

	public UnauthorisedStatusChangeException(String message) {
		super(message);
	}
	
	
}
