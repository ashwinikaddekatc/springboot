"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_Users;" + "\r\n" + 
"" + "\r\n" + 
"public interface UserDetailsService {" + "\r\n" + 
"" + "\r\n" + 
"	/* GET */" + "\r\n" + 
"	public List<Rn_Users> getAll();" + "\r\n" + 
"" + "\r\n" + 
"	/* GET by ID */" + "\r\n" + 
"	public ResponseEntity<Rn_Users> getById(Integer user_id) throws ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"	public Rn_Users create(Rn_Users user);" + "\r\n" + 
"" + "\r\n" + 
"	public ResponseEntity<Rn_Users> update(Integer user_id, Rn_Users userDetails) throws ResourceNotFoundException;" + "\r\n" + 
"	" + "\r\n" + 
"	public Map<String, Boolean> delete(Integer user_id) throws ResourceNotFoundException;" + "\r\n" + 
"	" + "\r\n" + 
"}" 
