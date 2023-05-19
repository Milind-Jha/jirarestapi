package com.jira.ticketrest.service;

import java.util.List;

import com.jira.ticketrest.bean.User;

public interface DemoUserService {
	
	public User getUser(String userId);
	
	public List<User> getAllUsers();
	
	public User createUser(User user);
	
	public boolean deleteUser(String userId);
	
	public User updateUser(String userId, User user);
}
