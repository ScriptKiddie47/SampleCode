package com.scripter.springbootrestfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoServiceImpl implements UserDaoService {
	private static List<User> usersList = new ArrayList<>();
	private static int userCount = 3;
	static {
		usersList.add(new User(1, "A", new Date()));
		usersList.add(new User(2, "B", new Date()));
		usersList.add(new User(3, "C", new Date()));
	}

	@Override
	public List<User> findAll() {
		return usersList;
	}


	@Override
	public User findOne(int id) {
		for (User user : usersList) {
			if (user.getId()==id) {
				return user;
			}
		}
		return null;
	}


	@Override
	public User saveUser(User save) {
		if(save.getId()==null) {
			save.setId(++userCount);
		}
		usersList.add(save);
		return save;
	}


	@Override
	public User deleteUser(int id) {
		for (Iterator<User> iterator = usersList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
