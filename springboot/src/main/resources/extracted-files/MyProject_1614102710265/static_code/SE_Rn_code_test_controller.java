"package com.realnet." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
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
"import com.realnet.model.Rn_code_test_t;" + "\r\n" + 
"import com.realnet.service.Rn_code_test_service;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class	Rn_code_test_controller{" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private	Rn_code_test_service rn_code_test_service;" + "\r\n" + 
"" + "\r\n" + 
"	// ==========GET ALL=========" + "\r\n" + 
"	@GetMapping(\"/rn_code_test_t\")" + "\r\n" + 
"	public List<Rn_code_test_t> getRn_code_test_tValues() {" + "\r\n" + 
"		return	rn_code_test_service.getAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==============GET BY ID=============" + "\r\n" + 
"	@GetMapping(\"/rn_code_test_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_code_test_t> getRn_code_test_tValuesById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		Rn_code_test_t rn_code_test_t = rn_code_test_service.getById(id);" + "\r\n" + 
"		return ResponseEntity.ok().body(rn_code_test_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================SAVE==================" + "\r\n" + 
"	@PostMapping(\"/rn_code_test_t\")" + "\r\n" + 
"	public	Rn_code_test_t saveRn_code_test_t(@Valid @RequestBody	Rn_code_test_t rn_code_test_t) {" + "\r\n" + 
"		return	rn_code_test_service.save(rn_code_test_t);" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================UPDATE==================" + "\r\n" + 
"	@PutMapping(\"/rn_code_test_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_code_test_t> updateRn_code_test_t(@PathVariable(value = \"id\") Integer id," + "\r\n" + 
"			@Valid @RequestBody	Rn_code_test_t rn_code_test_t) {" + "\r\n" + 
"		Rn_code_test_t updatedRn_code_test_t = rn_code_test_service.updateById(id, rn_code_test_t);" + "\r\n" + 
"		return ResponseEntity.ok(updatedRn_code_test_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// =================DELETE====================" + "\r\n" + 
"	@DeleteMapping(\"/rn_code_test_t/{id}\")" + "\r\n" + 
"	public Map<String, Boolean> deleteRn_code_test_tValue(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		rn_code_test_service.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"		return response;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
