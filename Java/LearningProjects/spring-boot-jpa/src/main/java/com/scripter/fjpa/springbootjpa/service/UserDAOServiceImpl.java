package com.scripter.fjpa.springbootjpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.scripter.fjpa.springbootjpa.entity.User;

@Repository
@Transactional
public class UserDAOServiceImpl implements UserDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Accept a User and Save user to DB
	 * @param user
	 * @return id of the created user
	 */
	@Override
	public long insert(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
