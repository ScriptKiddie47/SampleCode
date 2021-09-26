package com.scripter.springbootrestfulwebservice.user;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserResouce {
	public List<User> retrieveAllUser();

	public User retrieveUser(int id);
	
	public ResponseEntity<Object> createUser(User user);
	
	public void deleteUser(int id);
}
