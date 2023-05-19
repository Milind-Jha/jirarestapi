package com.jira.ticketrest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.ticketrest.bean.User;
import com.jira.ticketrest.dao.DemoUserDao;
import com.jira.ticketrest.exception.InvalidUserException;
import com.jira.ticketrest.service.DemoUserService;

@Service
public class DemoUserServiceImpl implements DemoUserService {
	
	
	private DemoUserDao demoUserDao;

	public DemoUserServiceImpl(DemoUserDao demoUserDao) {
		this.demoUserDao = demoUserDao;
	}

	@Override
	public User getUser(String userId) {
		if(demoUserDao.getAllUsers().contains(demoUserDao.getUser(userId)))
			return demoUserDao.getUser(userId);
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return demoUserDao.getAllUsers();
	}

	@Override
	public User createUser(User user) {
		return demoUserDao.addUser(user);
	}

	@Override
	public boolean deleteUser(String userId) {
		if(demoUserDao.getAllUsers().contains(demoUserDao.getUser(userId)))
			return demoUserDao.removeUser(userId);
		return false;
	}

	@Override
	public User updateUser(String userId, User user) throws InvalidUserException{
		// TODO Auto-generated method stub
		User currentUser = demoUserDao.getUser(userId);

		if(currentUser == null)
			throw new InvalidUserException("user with userId : "+userId+" not found");
		
		if(currentUser.getPassword().equals(user.getPassword())) {
			currentUser.setName(user.getName());
			currentUser.setEmail(user.getEmail());
			return currentUser;
		}
		throw new InvalidUserException("wrong password for userId : "+userId);

	}
	
}
