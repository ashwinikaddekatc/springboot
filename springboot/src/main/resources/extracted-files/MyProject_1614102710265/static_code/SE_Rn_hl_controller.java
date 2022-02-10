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
"import com.realnet.model.Rn_hl_header_t;" + "\r\n" + 
"import com.realnet.service.Rn_hl_service;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class	Rn_hl_controller {" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private	Rn_hl_service rn_hl_service;" + "\r\n" + 
"" + "\r\n" + 
"	// ==========GET ALL=========" + "\r\n" + 
"	@GetMapping(\"/rn_hl_header_t\")" + "\r\n" + 
"	public List<Rn_hl_header_t> getRn_hl_header_tValues() {" + "\r\n" + 
"		return	rn_hl_service.getAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==============GET BY ID=============" + "\r\n" + 
"	@GetMapping(\"/rn_hl_header_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_hl_header_t> getRn_hl_header_tValuesById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		Rn_hl_header_t rn_hl_header_t = rn_hl_service.getById(id);" + "\r\n" + 
"		return ResponseEntity.ok().body(rn_hl_header_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================SAVE==================" + "\r\n" + 
"	@PostMapping(\"/rn_hl_header_t\")" + "\r\n" + 
"	public	Rn_hl_header_t saveRn_hl_header_t(@Valid @RequestBody	Rn_hl_header_t rn_hl_header_t) {" + "\r\n" + 
"		return	rn_hl_service.save(rn_hl_header_t);" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ================UPDATE==================" + "\r\n" + 
"	@PutMapping(\"/rn_hl_header_t/{id}\")" + "\r\n" + 
"	public ResponseEntity<Rn_hl_header_t> updateRn_hl_header_t(@PathVariable(value = \"id\") Integer id," + "\r\n" + 
"			@Valid @RequestBody	Rn_hl_header_t rn_hl_header_t) {" + "\r\n" + 
"		Rn_hl_header_t updatedRn_hl_header_t = rn_hl_service.updateById(id, rn_hl_header_t);" + "\r\n" + 
"		return ResponseEntity.ok(updatedRn_hl_header_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// =================DELETE====================" + "\r\n" + 
"	@DeleteMapping(\"/rn_hl_header_t/{id}\")" + "\r\n" + 
"	public Map<String, Boolean> deleteRn_hl_header_tValue(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		rn_hl_service.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"		return response;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
