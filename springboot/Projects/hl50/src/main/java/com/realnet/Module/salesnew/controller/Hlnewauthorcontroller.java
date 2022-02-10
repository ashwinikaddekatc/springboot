package com.realnet.Module.salesnew.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.realnet.Module.salesnew.entity.*;
import com.realnet.Module.salesnew.repository.*;
import com.realnet.Module.salesnew.responce.*;
import com.realnet.Module.salesnew.service.*;
import com.realnet.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Teacher" })
public class Hlnewauthorcontroller{
	@Autowired
	private Hlnewauthorservice authorservice;


	// GET ALL
	@ApiOperation(value = "List of Teachers", response = Hlnewauthorres.class)
	@GetMapping("/author")
	public Hlnewauthorres getTeachers(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
		Hlnewauthorres resp = new Hlnewauthorres();
		Pageable paging = PageRequest.of(page, size);
		Page<Hlnewauthor> result = authorservice.getAll(paging);
		resp.setPageStats(result, true);
		resp.setAuthor(result.getContent());
		return resp;
	}

	// GET BY ID
	@ApiOperation(value = "Get a teacher", response = Hlnewauthor.class)
	@GetMapping("/author/{id}")
	public ResponseEntity<Hlnewauthor> getTeacherById(@PathVariable(value = "id") Integer id) {
		Hlnewauthor teacher = authorservice.getById(id);
		if (teacher == null) {
			throw new ResourceNotFoundException("teacher not found with id " + id);
		}
		return ResponseEntity.ok().body(teacher);
	}

	// SAVE
	@ApiOperation(value = "Add new teacher", response = Hlnewauthor.class)
	@PostMapping("/author")
	public ResponseEntity<Hlnewauthor> createTeacher(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@Valid @RequestBody Hlnewauthor teacher) {

		Hlnewauthor savedTeacher = authorservice.save(teacher);
		if (savedTeacher == null) {
			throw new ResourceNotFoundException("teacher is not saved");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
	}

	// UPDATE
	@ApiOperation(value = "update a teacher", response = Hlnewauthor.class)
	@PutMapping("/author/{id}")
	public ResponseEntity<Hlnewauthor> updateTeacher(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@PathVariable(value = "id") Integer id, @Valid @RequestBody Hlnewauthor teacher) {

		Hlnewauthor updatedTeacher = authorservice.updateById(id, teacher);
		if (updatedTeacher == null || id != updatedTeacher.getId()) {
			throw new ResourceNotFoundException("teacher not found with id " + id);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedTeacher);
	}

	@DeleteMapping("/author/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = "id") Integer id) {
		boolean deleted = authorservice.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		if (deleted) {
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	

}