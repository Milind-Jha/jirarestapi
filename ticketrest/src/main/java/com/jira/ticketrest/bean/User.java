package com.jira.ticketrest.bean;

import java.util.UUID;

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
	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password) {
		
		this.userId = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
}
