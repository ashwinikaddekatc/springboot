"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_Users;" + "\r\n" + 
"import com.realnet.repository.UserDetailsRepository;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class UserDetailsServiceImpl implements UserDetailsService {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private UserDetailsRepository userRepository;" + "\r\n" + 
"" + "\r\n" + 
"	/*GET*/" + "\r\n" + 
"	public List<Rn_Users> getAll() {" + "\r\n" + 
"		return userRepository.findAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	/*GET by ID*/" + "\r\n" + 
"	public ResponseEntity<Rn_Users> getById(Integer user_id) throws ResourceNotFoundException {" + "\r\n" + 
"		Rn_Users user = userRepository.findById(user_id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"User not found on :: \" + user_id));" + "\r\n" + 
"		return ResponseEntity.ok().body(user);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	public Rn_Users create(Rn_Users user) {" + "\r\n" + 
"		return userRepository.save(user);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public ResponseEntity<Rn_Users> update(Integer user_id, Rn_Users userDetails) throws ResourceNotFoundException {" + "\r\n" + 
"" + "\r\n" + 
"		Rn_Users rn_users = userRepository.findById(user_id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"User not found for this id :: \" + user_id));" + "\r\n" + 
"" + "\r\n" + 
"		rn_users.setUser_name(userDetails.getUser_name());" + "\r\n" + 
"		rn_users.setStart_date(userDetails.getStart_date());" + "\r\n" + 
"		rn_users.setEnd_date(userDetails.getEnd_date());" + "\r\n" + 
"		rn_users.setFirst_name(userDetails.getFirst_name());" + "\r\n" + 
"		rn_users.setMiddle_name(userDetails.getMiddle_name());" + "\r\n" + 
"		rn_users.setLast_name(userDetails.getLast_name());" + "\r\n" + 
"		rn_users.setContact_number(userDetails.getContact_number());" + "\r\n" + 
"		rn_users.setEmail_address(userDetails.getEmail_address());" + "\r\n" + 
"" + "\r\n" + 
"		final Rn_Users updatedUser = userRepository.save(rn_users);" + "\r\n" + 
"		return ResponseEntity.ok(updatedUser);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Map<String, Boolean> delete(Integer user_id) throws ResourceNotFoundException {" + "\r\n" + 
"		Rn_Users rn_users = userRepository.findById(user_id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Employee not found for this id :: \" + user_id));" + "\r\n" + 
"" + "\r\n" + 
"		userRepository.delete(rn_users);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"		return response;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
