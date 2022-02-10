"package com.realnet.comm." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.PageRequest;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.http.HttpHeaders;" + "\r\n" + 
"import org.springframework.http.HttpStatus;" + "\r\n" + 
"import org.springframework.http.MediaType;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestHeader;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"import com.realnet.comm.service.*;" + "\r\n" + 
"import com.realnet.comm.entity.author;" + "\r\n" + 
"import com.realnet.comm.response.authorres;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.Api;" + "\r\n" + 
"import io.swagger.annotations.ApiOperation;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@RequestMapping(value = \"/api\", produces = MediaType.APPLICATION_JSON_VALUE)" + "\r\n" + 
"@Api(tags = { \"Teacher\" })" + "\r\n" + 
"public class " + sbhlcontroller1 + "{"+
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private authorservice authorservice;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	// GET ALL" + "\r\n" + 
"	@ApiOperation(value = \"List of Teachers\", response = authorres.class)" + "\r\n" + 
"	@GetMapping(\"/author\")" + "\r\n" + 
"	public authorres getTeachers(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size) {" + "\r\n" + 
"		authorres resp = new authorres();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<author> result = authorservice.getAll(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setAuthor(result.getContent());" + "\r\n" + 
"		return resp;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// GET BY ID" + "\r\n" + 
"	@ApiOperation(value = \"Get a teacher\", response = author.class)" + "\r\n" + 
"	@GetMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<author> getTeacherById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		author teacher = authorservice.getById(id);" + "\r\n" + 
"		if (teacher == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(teacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// SAVE" + "\r\n" + 
"	@ApiOperation(value = \"Add new teacher\", response = author.class)" + "\r\n" + 
"	@PostMapping(\"/author\")" + "\r\n" + 
"	public ResponseEntity<author> createTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@Valid @RequestBody author teacher) {" + "\r\n" + 
"" + "\r\n" + 
"		author savedTeacher = authorservice.save(teacher);" + "\r\n" + 
"		if (savedTeacher == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher is not saved\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@ApiOperation(value = \"update a teacher\", response = author.class)" + "\r\n" + 
"	@PutMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<author> updateTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@PathVariable(value = \"id\") Integer id, @Valid @RequestBody author teacher) {" + "\r\n" + 
"" + "\r\n" + 
"		author updatedTeacher = authorservice.updateById(id, teacher);" + "\r\n" + 
"		if (updatedTeacher == null || id != updatedTeacher.getId()) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedTeacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@DeleteMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		boolean deleted = authorservice.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		if (deleted) {" + "\r\n" + 
"			response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.OK).body(response);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"}" 
