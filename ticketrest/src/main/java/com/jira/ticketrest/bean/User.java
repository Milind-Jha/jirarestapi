package com.jira.ticketrest.bean;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	private String userId;
	@NotEmpty(message = "name cannot be null")
	@Size(min = 3,max = 20,message = "name has to be between 3 to 20 characters")
	private String name;
	@Size(min = 8,max = 20,message = "name has to be between 8 to 20 characters")
	@Email(message = "Enter valid mail")
	private String email;
	@NotEmpty(message = "password cannot be null")
	@Size(min = 8,max = 20,message = "password has to be between 8 to 20 characters")
	private String password;
	
	public User(String name, String email, String password) {
		
		this.userId = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
}
