package com.scripter.fjpa.springbootjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scripter.fjpa.springbootjpa.entity.User;
import com.scripter.fjpa.springbootjpa.service.UserDAOService;

@Component
public class UserDaoCommandLineRunner implements CommandLineRunner {
	// To make it CommandLineRunner we need to implement CommandLineRunner

	// Logged SLF4J
	private static final Logger log = LoggerFactory.getLogger(UserDaoCommandLineRunner.class);

	@Autowired
	private UserDAOService userDaoService;

	@Override
	public void run(String... args) throws Exception {
		User jack = new User("Jack", "admin");
		// Lets save the above user to the Database

		long id = userDaoService.insert(jack);
		log.info("New ID generated " + id);
		log.info("For USER " + jack.toString());
	}
}
