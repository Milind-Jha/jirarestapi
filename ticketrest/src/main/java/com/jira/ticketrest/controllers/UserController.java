package com.jira.ticketrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.ticketrest.bean.User;
import com.jira.ticketrest.dao.DemoUserDao;
import com.jira.ticketrest.exception.InvalidUserException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private DemoUserDao demoDao;
	
	public UserController(DemoUserDao dao) {
		this.demoDao= dao;
	}
	
	@GetMapping("")
	public List<User> check() {
		return demoDao.getAllUsers();
	}
	
	@PostMapping("")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {	
		return ResponseEntity.status(HttpStatus.CREATED).body(demoDao.addUser(user));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		if(demoDao.getUser(userId)!=null)
			return ResponseEntity.ok().body(demoDao.getUser(userId));
		throw new InvalidUserException("user with userId : "+userId+" not found");
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) {
		if( demoDao.removeUser(userId))
			return ResponseEntity.ok().body(Boolean.TRUE);
		throw new InvalidUserException("user with userId : "+userId+" not found");
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId,@Valid @RequestBody User user){
		if(demoDao.getUser(userId)!=null) {
			demoDao.getUser(userId).setName(user.getName());
			demoDao.getUser(userId).setEmail(user.getEmail());
			demoDao.getUser(userId).setPassword(user.getPassword());
			return ResponseEntity.ok().body(demoDao.getUser(userId));
		}
		throw new InvalidUserException("user with userId : "+userId+" not found");
	}
	
}
