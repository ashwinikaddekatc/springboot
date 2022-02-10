"package com.realnet." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.web.bind.annotation.CrossOrigin;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_Users;" + "\r\n" + 
"import com.realnet.service.UserDetailsService;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class Rn_User_Details_Controller {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	UserDetailsService userDetailsService;" + "\r\n" + 
"" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * Get All users." + "\r\n" + 
"	 */" + "\r\n" + 
"	@GetMapping(\"/users\")" + "\r\n" + 
"	public List<Rn_Users> getUsers() {" + "\r\n" + 
"		return userDetailsService.getAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * Gets users by id." + "\r\n" + 
"	 */" + "\r\n" + 
"	@GetMapping(\"/users/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_Users> getUsersById(@PathVariable(value = \"id\") Integer user_id)" + "\r\n" + 
"			throws ResourceNotFoundException {" + "\r\n" + 
"		return userDetailsService.getById(user_id);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * create new user" + "\r\n" + 
"	 */" + "\r\n" + 
"	@PostMapping(\"/users\")" + "\r\n" + 
"	Rn_Users addUser(@Valid @RequestBody Rn_Users user) {" + "\r\n" + 
"		return userDetailsService.create(user);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * update user" + "\r\n" + 
"	 */" + "\r\n" + 
"	@PutMapping(\"/users/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_Users> updateUser(@PathVariable(value = \"id\") Integer user_id," + "\r\n" + 
"			@Valid @RequestBody Rn_Users userDetails) throws ResourceNotFoundException {" + "\r\n" + 
"" + "\r\n" + 
"		return userDetailsService.update(user_id, userDetails);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * delete user" + "\r\n" + 
"	 */" + "\r\n" + 
"	@DeleteMapping(\"/users/{id}\")" + "\r\n" + 
"	public Map<String, Boolean> deleteUser(@PathVariable(value = \"id\") Integer user_id)" + "\r\n" + 
"			throws ResourceNotFoundException {" + "\r\n" + 
"		return userDetailsService.delete(user_id);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
