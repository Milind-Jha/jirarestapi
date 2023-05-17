package com.jira.ticketrest.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.jira.ticketrest.exception.IllegalTicketStatusChangeException;
import com.jira.ticketrest.exception.InvalidUserException;
import com.jira.ticketrest.exception.TicketNotFoundException;
import com.jira.ticketrest.exception.UnauthorisedStatusChangeException;
import com.jira.ticketrest.payload.ApiResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(IllegalTicketStatusChangeException.class)
	public ResponseEntity<ApiResponse> handleIllegalTicketStatusChangeException(IllegalTicketStatusChangeException exception){
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.BAD_REQUEST).build();
		return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorisedStatusChangeException.class)
	public ResponseEntity<ApiResponse> handleUnauthorisedStatusChangeException(UnauthorisedStatusChangeException exception){
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.UNAUTHORIZED).build();
		return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<ApiResponse> handleTicketNotFoundException(TicketNotFoundException exception){
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ApiResponse> handleInvalidUserException(TicketNotFoundException exception){
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse> handleIllegalStateException(IllegalStateException exception){
		ApiResponse apiResponse;
		if(exception.getMessage().equalsIgnoreCase("Invalid Credentials")){
			apiResponse= ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.UNAUTHORIZED).build();
			return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
		}
		else {
			apiResponse = ApiResponse.builder().message(exception.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}
	
}
