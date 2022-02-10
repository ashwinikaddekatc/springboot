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
"import com.realnet.model.Instructor;" + "\r\n" + 
"import com.realnet.service.InstructorService;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class InstructorController {" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private InstructorService instructorService;" + "\r\n" + 
"" + "\r\n" + 
"	// GET ALL" + "\r\n" + 
"	@GetMapping(\"/instructors\")" + "\r\n" + 
"	public List<Instructor> getInstructors() {" + "\r\n" + 
"		return instructorService.getAllInstructor();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// GET BY ID" + "\r\n" + 
"	@GetMapping(\"/instructors/{id}\")" + "\r\n" + 
"	public ResponseEntity<Instructor> getInstructorById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		Instructor instructor = instructorService.getInstructorById(id);" + "\r\n" + 
"		return ResponseEntity.ok().body(instructor);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// SAVE" + "\r\n" + 
"	@PostMapping(\"/instructors\")" + "\r\n" + 
"	public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {" + "\r\n" + 
"		return instructorService.saveInstructor(instructor);" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@PutMapping(\"/instructors/{id}\")" + "\r\n" + 
"	public ResponseEntity<Instructor> updateInstructor(@PathVariable(value = \"id\") Integer id," + "\r\n" + 
"			@Valid @RequestBody Instructor instructor) {" + "\r\n" + 
"		Instructor updatedInstructor = instructorService.updateInstructorById(id, instructor);" + "\r\n" + 
"		return ResponseEntity.ok(updatedInstructor);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// DELETE" + "\r\n" + 
"	@DeleteMapping(\"/instructors/{id}\")" + "\r\n" + 
"	public Map<String, Boolean> deleteUser(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		instructorService.deleteInstructorById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"		return response;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
