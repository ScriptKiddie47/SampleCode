package com.scripter.springbootrestfulwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResouceImpl implements UserResouce {

	@Autowired
	private UserDaoService service;

	@Override
	@GetMapping("/users")
	public List<User> retrieveAllUser() {
		return service.findAll();
	}

	@Override
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User findOne = service.findOne(id);
		if (findOne == null)
			throw new UsernotFoundException("id : " + id);
		return findOne;
	}

	@Override
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if (user == null) {
			throw new UsernotFoundException("id:" + id);
		}
	}
}
