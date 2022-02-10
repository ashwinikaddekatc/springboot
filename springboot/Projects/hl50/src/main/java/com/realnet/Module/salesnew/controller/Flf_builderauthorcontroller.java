package com.realnet.Module.salesnew.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.realnet.Module.salesnew.entity.*;
import com.realnet.Module.salesnew.repository.*;
import com.realnet.Module.salesnew.responce.*;
import com.realnet.Module.salesnew.service.*;
import com.realnet.exceptions.ResourceNotFoundException;

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
public class Flf_builderauthorcontroller{
	@Autowired
	private Flf_builderauthorservice authorservice;


	// GET ALL
	@ApiOperation(value = "List of Teachers", response = Flf_builderauthorres.class)
	@GetMapping("/author")
	public Flf_builderauthorres getTeachers(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
		Flf_builderauthorres resp = new Flf_builderauthorres();
		Pageable paging = PageRequest.of(page, size);
		Page<Flf_builderauthor> result = authorservice.getAll(paging);
		resp.setPageStats(result, true);
		resp.setAuthor(result.getContent());
		return resp;
	}

	// GET BY ID
	@ApiOperation(value = "Get a teacher", response = Flf_builderauthor.class)
	@GetMapping("/author/{id}")
	public ResponseEntity<Flf_builderauthor> getTeacherById(@PathVariable(value = "id") Integer id) {
		Flf_builderauthor teacher = authorservice.getById(id);
		if (teacher == null) {
			throw new ResourceNotFoundException("teacher not found with id " + id);
		}
		return ResponseEntity.ok().body(teacher);
	}

	// SAVE
	@ApiOperation(value = "Add new teacher", response = Flf_builderauthor.class)
	@PostMapping("/author")
	public ResponseEntity<Flf_builderauthor> createTeacher(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@Valid @RequestBody Flf_builderauthor teacher) {

		Flf_builderauthor savedTeacher = authorservice.save(teacher);
		if (savedTeacher == null) {
			throw new ResourceNotFoundException("teacher is not saved");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
	}

	// UPDATE
	@ApiOperation(value = "update a teacher", response = Flf_builderauthor.class)
	@PutMapping("/author/{id}")
	public ResponseEntity<Flf_builderauthor> updateTeacher(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@PathVariable(value = "id") Integer id, @Valid @RequestBody Flf_builderauthor teacher) {

		Flf_builderauthor updatedTeacher = authorservice.updateById(id, teacher);
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