package com.jira.ticketrest.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.jira.ticketrest.bean.User;

@Repository
public class DemoUserDao {
	
	private static List<User> users = new LinkedList();
	
	private static List<String> userIds = new ArrayList();
	
	
	static {
		
		for(int i=0;i<3;i++) {
			userIds.add(UUID.randomUUID().toString());
		}
		
		users.add(new User(userIds.get(0),"Milind","milind.jha@devkraft.in","abcd"));
		users.add(new User(userIds.get(1),"Abhishek","abhishek@devkraft.in","dlwv"));
		users.add(new User(userIds.get(2),"Sachin","sachin@devkraft.in","efgh"));
	}
	
	public List<String> getUserIds(){
		return userIds;
	}
	
	public User addUser(User user) {
		User newUser = new User(user.getName(),user.getEmail(),user.getPassword());
		users.add(newUser);
		return newUser;
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User getUser(String userId) {
		for(User user: users) {
			if(user.getUserId().equals(userId))
				return user;
		}
		return null;
	}
	
	public boolean removeUser(String userId) {
		for(User user: users) {
			if(user.getUserId().equals(userId)) {
				return users.remove(user);
			}
		}
		return false;
	}
	
}
