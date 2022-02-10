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
"import com.realnet.model.Rn_header_only_test2_t;" + "\r\n" + 
"import com.realnet.service.Rn_header_only_test2_service;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class	Rn_header_only_test2_controller{" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private	Rn_header_only_test2_service rn_header_only_test2_service;" + "\r\n" + 
"" + "\r\n" + 
"	// ==========GET ALL=========" + "\r\n" + 
"	@GetMapping(\"/rn_header_only_test2_t\")" + "\r\n" + 
"	public List<Rn_header_only_test2_t> getRn_header_only_test2_tValues() {" + "\r\n" + 
"		return	rn_header_only_test2_service.getAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==============GET BY ID=============" + "\r\n" + 
"	@GetMapping(\"/rn_header_only_test2_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_header_only_test2_t> getRn_header_only_test2_tValuesById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		Rn_header_only_test2_t rn_header_only_test2_t = rn_header_only_test2_service.getById(id);" + "\r\n" + 
"		return ResponseEntity.ok().body(rn_header_only_test2_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================SAVE==================" + "\r\n" + 
"	@PostMapping(\"/rn_header_only_test2_t\")" + "\r\n" + 
"	public	Rn_header_only_test2_t saveRn_header_only_test2_t(@Valid @RequestBody	Rn_header_only_test2_t rn_header_only_test2_t) {" + "\r\n" + 
"		return	rn_header_only_test2_service.save(rn_header_only_test2_t);" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================UPDATE==================" + "\r\n" + 
"	@PutMapping(\"/rn_header_only_test2_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_header_only_test2_t> updateRn_header_only_test2_t(@PathVariable(value = \"id\") Integer id," + "\r\n" + 
"			@Valid @RequestBody	Rn_header_only_test2_t rn_header_only_test2_t) {" + "\r\n" + 
"		Rn_header_only_test2_t updatedRn_header_only_test2_t = rn_header_only_test2_service.updateById(id, rn_header_only_test2_t);" + "\r\n" + 
"		return ResponseEntity.ok(updatedRn_header_only_test2_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// =================DELETE====================" + "\r\n" + 
"	@DeleteMapping(\"/rn_header_only_test2_t/{id}\")" + "\r\n" + 
"	public Map<String, Boolean> deleteRn_header_only_test2_tValue(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		rn_header_only_test2_service.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"		return response;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
