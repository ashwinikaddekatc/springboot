package com.realnet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.model.Instructor;
import com.realnet.service.InstructorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;

	// GET ALL
	@GetMapping("/instructors")
	public List<Instructor> getInstructors() {
		return instructorService.getAllInstructor();
	}

	// GET BY ID
	@GetMapping("/instructors/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable(value = "id") Integer id) {
		Instructor instructor = instructorService.getInstructorById(id);
		return ResponseEntity.ok().body(instructor);
	}

	// SAVE
	@PostMapping("/instructors")
	public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {
		return instructorService.saveInstructor(instructor);

	}

	// UPDATE
	@PutMapping("/instructors/{id}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Instructor instructor) {
		Instructor updatedInstructor = instructorService.updateInstructorById(id, instructor);
		return ResponseEntity.ok(updatedInstructor);
	}

	// DELETE
	@DeleteMapping("/instructors/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer id) {
		instructorService.deleteInstructorById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}