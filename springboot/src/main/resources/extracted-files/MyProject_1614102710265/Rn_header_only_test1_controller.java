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

import com.realnet.model.Rn_header_only_test1_t;
import com.realnet.service.Rn_header_only_test1_service;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class	Rn_header_only_test1_controller{
	@Autowired
	private	Rn_header_only_test1_service rn_header_only_test1_service;

	// ==========GET ALL=========
	@GetMapping("/rn_header_only_test1_t")
	public List<Rn_header_only_test1_t> getRn_header_only_test1_tValues() {
		return	rn_header_only_test1_service.getAll();
	}

	// ==============GET BY ID=============
	@GetMapping("/rn_header_only_test1_t/{id}")
	public ResponseEntity<Rn_header_only_test1_t> getRn_header_only_test1_tValuesById(@PathVariable(value = "id") Integer id) {
		Rn_header_only_test1_t rn_header_only_test1_t = rn_header_only_test1_service.getById(id);
		return ResponseEntity.ok().body(rn_header_only_test1_t);
	}

	// ================SAVE==================
	@PostMapping("/rn_header_only_test1_t")
	public	Rn_header_only_test1_t saveRn_header_only_test1_t(@Valid @RequestBody	Rn_header_only_test1_t rn_header_only_test1_t) {
		return	rn_header_only_test1_service.save(rn_header_only_test1_t);

	}

	// ================UPDATE==================
	@PutMapping("/rn_header_only_test1_t/{id}")
	public ResponseEntity<Rn_header_only_test1_t> updateRn_header_only_test1_t(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody	Rn_header_only_test1_t rn_header_only_test1_t) {
		Rn_header_only_test1_t updatedRn_header_only_test1_t = rn_header_only_test1_service.updateById(id, rn_header_only_test1_t);
		return ResponseEntity.ok(updatedRn_header_only_test1_t);
	}

	// =================DELETE====================
	@DeleteMapping("/rn_header_only_test1_t/{id}")
	public Map<String, Boolean> deleteRn_header_only_test1_tValue(@PathVariable(value = "id") Integer id) {
		rn_header_only_test1_service.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
        
	}
}
