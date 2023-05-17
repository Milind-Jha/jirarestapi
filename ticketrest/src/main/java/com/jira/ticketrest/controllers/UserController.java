package com.jira.ticketrest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.ticketrest.bean.User;
import com.jira.ticketrest.dao.DemoUserDao;

@RestController
public class UserController {
	
	
	private DemoUserDao demoDao;
	
	public UserController(DemoUserDao dao) {
		this.demoDao= dao;
	}
	
	@GetMapping("/users")
	public List<User> check() {
		return demoDao.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return demoDao.addUser(user);
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable String userId) {
		return demoDao.getUser(userId);
	}
	
	@DeleteMapping("/users/{userId}")
	public boolean deleteUser(@PathVariable String userId) {
		return demoDao.removeUser(userId);
	}
	
	
}
