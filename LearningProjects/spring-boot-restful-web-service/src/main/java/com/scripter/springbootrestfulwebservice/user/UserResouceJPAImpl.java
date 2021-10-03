package com.scripter.springbootrestfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import com.scripter.springbootrestfulwebservice.post.Post;
import com.scripter.springbootrestfulwebservice.post.PostRepository;

@RestController
public class UserResouceJPAImpl implements UserResouce {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	@GetMapping("jpa/users")
	public List<User> retrieveAllUser() {
		return userRepository.findAll();
	}

	@Override
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) throws NoSuchElementException {
		Optional<User> findOne = userRepository.findById(id);
		if (!findOne.isPresent())
			throw new UsernotFoundException("id : " + id);
		return findOne.get();
	}

	@Override
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPostsForId(@PathVariable Integer id) throws UsernotFoundException {
		Optional<User> findById = userRepository.findById(id);
		if (!findById.isPresent()) {
			throw new UsernotFoundException("id : " + id);
		}
		return findById.get().getPost();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Integer id,@RequestBody Post post) throws UsernotFoundException {
		Optional<User> findById = userRepository.findById(id);
		if (!findById.isPresent()) {
			throw new UsernotFoundException("id : " + id);
		}
		post.setUser(findById.get());
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
