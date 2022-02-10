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

import com.realnet.model.Rn_hl_header_t;
import com.realnet.service.Rn_hl_service;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class	Rn_hl_controller {
	@Autowired
	private	Rn_hl_service rn_hl_service;

	// ==========GET ALL=========
	@GetMapping("/rn_hl_header_t")
	public List<Rn_hl_header_t> getRn_hl_header_tValues() {
		return	rn_hl_service.getAll();
	}

	// ==============GET BY ID=============
	@GetMapping("/rn_hl_header_t/{id}")
	public ResponseEntity<Rn_hl_header_t> getRn_hl_header_tValuesById(@PathVariable(value = "id") Integer id) {
		Rn_hl_header_t rn_hl_header_t = rn_hl_service.getById(id);
		return ResponseEntity.ok().body(rn_hl_header_t);
	}

	// ================SAVE==================
	@PostMapping("/rn_hl_header_t")
	public	Rn_hl_header_t saveRn_hl_header_t(@Valid @RequestBody	Rn_hl_header_t rn_hl_header_t) {
		return	rn_hl_service.save(rn_hl_header_t);

	}

	// ================UPDATE==================
	@PutMapping("/rn_hl_header_t/{id}")
	public ResponseEntity<Rn_hl_header_t> updateRn_hl_header_t(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody	Rn_hl_header_t rn_hl_header_t) {
		Rn_hl_header_t updatedRn_hl_header_t = rn_hl_service.updateById(id, rn_hl_header_t);
		return ResponseEntity.ok(updatedRn_hl_header_t);
	}

	// =================DELETE====================
	@DeleteMapping("/rn_hl_header_t/{id}")
	public Map<String, Boolean> deleteRn_hl_header_tValue(@PathVariable(value = "id") Integer id) {
		rn_hl_service.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
