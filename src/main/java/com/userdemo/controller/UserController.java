package com.userdemo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userdemo.entity.User;
import com.userdemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<List<User>> listUsers() {
		ResponseEntity<List<User>> response = null;
		try {
			List<User> users = userService.listUsers();
			response = ResponseEntity.ok(users);
		} catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	private ResponseEntity<User> handleException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		ResponseEntity<User> response = null;
		try {
			User user = userService.getUser(id);
			response = ResponseEntity.ok(user);
		} catch (Exception e) {
			response = handleException(e);
		}
		return response;
	}

	@PostMapping("")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		ResponseEntity<User> response = null;
		try {
			user = userService.createUser(user.getName(), user.getEmail());
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
					.toUri();
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).location(location).build();
		} catch (Exception e) {
			response = handleException(e);
		}
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
		ResponseEntity<User> response = null;
		try {
			userService.updateUser(id, user);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			response = handleException(e);
		}
		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		ResponseEntity<User> response = null;
		try {
			userService.deleteUser(id);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			response = handleException(e);
		}
		return response;
	}
}