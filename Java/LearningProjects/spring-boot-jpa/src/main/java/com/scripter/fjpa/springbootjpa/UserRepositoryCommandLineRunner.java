package com.scripter.fjpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scripter.fjpa.springbootjpa.entity.User;
import com.scripter.fjpa.springbootjpa.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
	// To make it CommandLineRunner we need to implement CommandLineRunner

	// Logged SLF4J
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {
		User jill = new User("Jill", "admin");
		// Lets save the above user to the Database
		// Wether we update or insert , we use the same method 'save'.
		userRepo.save(jill);
		log.info("New ID generated " + jill);
		log.info("For USER " + jill.toString());

		// Lets use the findById , it returns an optional
		Optional<User> userWithId = userRepo.findById(1L);
		log.info("User is retrieved : " + userWithId.toString());

		// lets use findAll
		List<User> findAll = userRepo.findAll();
		log.info("List of all User is retrieved : " + Arrays.toString(findAll.toArray()));
	}
}
