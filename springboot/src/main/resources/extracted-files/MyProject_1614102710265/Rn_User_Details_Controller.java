package com.realnet.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_Users;
import com.realnet.service.UserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class Rn_User_Details_Controller {

	@Autowired
	UserDetailsService userDetailsService;

	/**
	 * Get All users.
	 */
	@GetMapping("/users")
	public List<Rn_Users> getUsers() {
		return userDetailsService.getAll();
	}

	/**
	 * Gets users by id.
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<Rn_Users> getUsersById(@PathVariable(value = "id") Integer user_id)
			throws ResourceNotFoundException {
		return userDetailsService.getById(user_id);
	}

	/**
	 * create new user
	 */
	@PostMapping("/users")
	Rn_Users addUser(@Valid @RequestBody Rn_Users user) {
		return userDetailsService.create(user);
	}

	/**
	 * update user
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<Rn_Users> updateUser(@PathVariable(value = "id") Integer user_id,
			@Valid @RequestBody Rn_Users userDetails) throws ResourceNotFoundException {

		return userDetailsService.update(user_id, userDetails);
	}

	/**
	 * delete user
	 */
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer user_id)
			throws ResourceNotFoundException {
		return userDetailsService.delete(user_id);
	}

}
