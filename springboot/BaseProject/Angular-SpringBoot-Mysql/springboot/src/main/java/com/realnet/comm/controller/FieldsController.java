package com.realnet.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;


import com.realnet.comm.entity.Fields;
import com.realnet.comm.entity.Teacher;
import com.realnet.comm.response.DepartmentResponse;
import com.realnet.comm.response.fieldsResponce;
import com.realnet.comm.service.fieldServiceImp;
import com.realnet.exceptions.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Fields" })
public class FieldsController {
	@Autowired
	private fieldServiceImp fieldServiceImp;
	

	//upload files
		@PostMapping("/upload-file")
		public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
		{
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
			System.out.println(file.getContentType());
			return ResponseEntity.ok("working");
		}
	
	//get all
	@ApiOperation(value = "List of Fields", response = DepartmentResponse.class)
	@GetMapping("/fields")
	public fieldsResponce getDepartments(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "40", required = false) Integer size) 
	{
		fieldsResponce resp = new fieldsResponce();
		Pageable paging = PageRequest.of(page, size);
		Page<Fields> result = fieldServiceImp.getAll(paging);
		resp.setPageStats(result, true);
		resp.setItems(result.getContent());
		return resp;
	}

	// GET BY ID
			@ApiOperation(value = "Get a fields", response = Fields.class)
			@GetMapping("/fields/{id}")
			public ResponseEntity<Fields> getFieldsId(@PathVariable(value = "id") Integer id) {
				Fields fields =fieldServiceImp.getbyid(id);
				if (fields == null) {
					throw new ResourceNotFoundException("department not found with id " + id);
				}
				return ResponseEntity.ok().body(fields);
			}
			
			//save data
			@ApiOperation(value = "Add A New fields", response = Fields.class)
			@PostMapping("/fields")
			public ResponseEntity<List<Fields>> createFields(
				@Valid	@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
					 @RequestBody List<Fields> fields) 
					{
				
				List<Fields> savedRn_Forms_Setup = fieldServiceImp.save(fields);
				return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Forms_Setup);
			}
			
			 // UPDATE
			@ApiOperation(value = "update a fields", response = Fields.class)
			@PutMapping("/fields/{id}")
			public ResponseEntity<Fields> updateFields(
					@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
					@PathVariable(value = "id") Integer id, @Valid @RequestBody Fields fields) {

				Fields updatedFields = fieldServiceImp.updateById(id, fields);
				if (updatedFields == null || id != updatedFields.getId()) {
					throw new ResourceNotFoundException("teacher not found with id " + id);
				}
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedFields);
			}
			// DELETE
						@DeleteMapping("/fields/{id}")
						public ResponseEntity<Map<String, Boolean>> deletefields(@PathVariable(value = "id") Integer id) {
							boolean deleted = fieldServiceImp.deleteById(id);
							Map<String, Boolean> response = new HashMap<>();
							if (deleted) {
								response.put("deleted", Boolean.TRUE);
								return ResponseEntity.status(HttpStatus.OK).body(response);
							}
							return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
						}
}
