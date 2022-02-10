package com.realnet.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_Users;

public interface UserDetailsService {

	/* GET */
	public List<Rn_Users> getAll();

	/* GET by ID */
	public ResponseEntity<Rn_Users> getById(Integer user_id) throws ResourceNotFoundException;

	public Rn_Users create(Rn_Users user);

	public ResponseEntity<Rn_Users> update(Integer user_id, Rn_Users userDetails) throws ResourceNotFoundException;
	
	public Map<String, Boolean> delete(Integer user_id) throws ResourceNotFoundException;
	
}
