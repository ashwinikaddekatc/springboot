package com.realnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_Users;
import com.realnet.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userRepository;

	/*GET*/
	public List<Rn_Users> getAll() {
		return userRepository.findAll();
	}

	/*GET by ID*/
	public ResponseEntity<Rn_Users> getById(Integer user_id) throws ResourceNotFoundException {
		Rn_Users user = userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + user_id));
		return ResponseEntity.ok().body(user);
	}

	
	public Rn_Users create(Rn_Users user) {
		return userRepository.save(user);
	}

	public ResponseEntity<Rn_Users> update(Integer user_id, Rn_Users userDetails) throws ResourceNotFoundException {

		Rn_Users rn_users = userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user_id));

		rn_users.setUser_name(userDetails.getUser_name());
		rn_users.setStart_date(userDetails.getStart_date());
		rn_users.setEnd_date(userDetails.getEnd_date());
		rn_users.setFirst_name(userDetails.getFirst_name());
		rn_users.setMiddle_name(userDetails.getMiddle_name());
		rn_users.setLast_name(userDetails.getLast_name());
		rn_users.setContact_number(userDetails.getContact_number());
		rn_users.setEmail_address(userDetails.getEmail_address());

		final Rn_Users updatedUser = userRepository.save(rn_users);
		return ResponseEntity.ok(updatedUser);
	}

	public Map<String, Boolean> delete(Integer user_id) throws ResourceNotFoundException {
		Rn_Users rn_users = userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + user_id));

		userRepository.delete(rn_users);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
