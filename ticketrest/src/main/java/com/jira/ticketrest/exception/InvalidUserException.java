package com.jira.ticketrest.exception;

public class InvalidUserException extends RuntimeException{

	public InvalidUserException(String message) {
		super(message);
	}
	
}
