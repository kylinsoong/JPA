package com.kylin.jpa.service;

import java.util.List;

import com.kylin.jpa.po.User;

public interface FacadeService {
	
	public void addUser(User user);
	public void addUsers(List<User> users);
	
	public List<User> getUsers();
	public User getUser(long id);
	
	public void removeUser(long id);
	
	public void updateUser(User user);
	
	public String ping();

}
