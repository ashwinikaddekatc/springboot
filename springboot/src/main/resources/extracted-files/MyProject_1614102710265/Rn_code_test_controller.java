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

import com.realnet.model.Rn_code_test_t;
import com.realnet.service.Rn_code_test_service;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class	Rn_code_test_controller{
	@Autowired
	private	Rn_code_test_service rn_code_test_service;

	// ==========GET ALL=========
	@GetMapping("/rn_code_test_t")
	public List<Rn_code_test_t> getRn_code_test_tValues() {
		return	rn_code_test_service.getAll();
	}

	// ==============GET BY ID=============
	@GetMapping("/rn_code_test_t/{id}")
	public ResponseEntity<Rn_code_test_t> getRn_code_test_tValuesById(@PathVariable(value = "id") Integer id) {
		Rn_code_test_t rn_code_test_t = rn_code_test_service.getById(id);
		return ResponseEntity.ok().body(rn_code_test_t);
	}

	// ================SAVE==================
	@PostMapping("/rn_code_test_t")
	public	Rn_code_test_t saveRn_code_test_t(@Valid @RequestBody	Rn_code_test_t rn_code_test_t) {
		return	rn_code_test_service.save(rn_code_test_t);

	}

	// ================UPDATE==================
	@PutMapping("/rn_code_test_t/{id}")
	public ResponseEntity<Rn_code_test_t> updateRn_code_test_t(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody	Rn_code_test_t rn_code_test_t) {
		Rn_code_test_t updatedRn_code_test_t = rn_code_test_service.updateById(id, rn_code_test_t);
		return ResponseEntity.ok(updatedRn_code_test_t);
	}

	// =================DELETE====================
	@DeleteMapping("/rn_code_test_t/{id}")
	public Map<String, Boolean> deleteRn_code_test_tValue(@PathVariable(value = "id") Integer id) {
		rn_code_test_service.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}