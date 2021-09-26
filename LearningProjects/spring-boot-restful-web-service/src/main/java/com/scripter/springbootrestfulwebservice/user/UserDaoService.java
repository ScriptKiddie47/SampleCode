package com.scripter.springbootrestfulwebservice.user;

import java.util.List;

public interface UserDaoService {
	public List<User> findAll();

	public User saveUser(User save);

	public User findOne(int id);
	
	public User deleteUser(int id);
	
}
