package com.realnet.fnd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.realnet.comm.entity.Teacher;
import com.realnet.exceptions.ResourceNotFoundException;
import com.realnet.fnd.entity.Rn_Ext_Fields;
import com.realnet.fnd.service.ExtFieldService;
import com.realnet.users.entity.User;
import com.realnet.users.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Extension Fields" })
public class Rn_ExtensionController {

	@Value("${angularProjectPath}")
	private String angularProjectPath;

	@Autowired
	private ExtFieldService extFieldService;
	
	@Autowired
	private UserService userService;

//	@Autowired
//	private TokenUtil tokenUtil;

	// GET ALL
	@ApiOperation(value = "List of Extensions")
	@GetMapping("/extension")
	public ResponseEntity<List<Rn_Ext_Fields>> getExtensions() {
		List<Rn_Ext_Fields> result = extFieldService.getAll();
		return ResponseEntity.ok().body(result);
	}

	// GET BY ID
	@ApiOperation(value = "Get an Extension", response = Teacher.class)
	@GetMapping("/extension/{id}")
	public ResponseEntity<Rn_Ext_Fields> getExtensionById(@PathVariable(value = "id") int id) {
		Rn_Ext_Fields rn_ext_fields = extFieldService.getById(id);
		return ResponseEntity.ok().body(rn_ext_fields);
	}

	// SAVE
	@ApiOperation(value = "Add new Extension", response = Rn_Ext_Fields.class)
	@PostMapping("/extension")
	public ResponseEntity<Rn_Ext_Fields> saveExtension(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@Valid @RequestBody Rn_Ext_Fields rn_ext_fields) {
//		String userId = tokenUtil.getUserId(authToken);
//		rn_ext_fields.setCreatedBy(userId);
//		rn_ext_fields.setAccountId(userId);
		User loggedInUser = userService.getLoggedInUser();
		
		rn_ext_fields.setAccount_id(loggedInUser.getUserId());
		Rn_Ext_Fields savedRn_Ext_Fields = extFieldService.save(rn_ext_fields);
		if (savedRn_Ext_Fields == null) {
			throw new ResourceNotFoundException("Rn_Ext_Field is not saved");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Ext_Fields);
	}

	// UPDATE
	@ApiOperation(value = "Update an Extension", response = Rn_Ext_Fields.class)
	@PutMapping("/extension/{id}")
	public ResponseEntity<Rn_Ext_Fields> updateRn_Ext_Field(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@PathVariable(value = "id") int id, @Valid @RequestBody Rn_Ext_Fields rn_ext_fields) {
//		String userId = tokenUtil.getUserId(authToken);
//		// teacher.setAccountId(userId);
//		rn_ext_fields.setUpdatedBy(userId);
		Rn_Ext_Fields updatedRn_Ext_Field = extFieldService.updateById(id, rn_ext_fields);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedRn_Ext_Field);
	}

	// DELETE
	@DeleteMapping("/extension/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRn_Ext_Field(@PathVariable(value = "id") int id) {
		boolean deleted = extFieldService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		if (deleted) {
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

//	@GetMapping("/lookup_values")
//	public ResponseEntity<List<String>> getLookUps() {
//		List<String> result = extFieldService.getLookupValues();
//		System.out.println(result);
//		return ResponseEntity.ok().body(result);
//	}
//
//	@GetMapping("/datatypes")
//	public ResponseEntity<List<String>> getDataTypes() {
//		List<String> result = extFieldService.getDataTypeValues();
//		System.out.println(result);
//		return ResponseEntity.ok().body(result);
//	}

	//extension builder for header
//	@GetMapping("/extension_build")
//	public ResponseEntity<Map<String,Boolean>> buildExtension(
//			@RequestParam(value = "account_id", required = false) String account_id,
//			@RequestParam(value = "form_code", required = false) String form_code) {
//		
//		System.out.println("Acc Id" + account_id + " Form Code = " + form_code);
//		extFieldService.buildExtensionByFormCode(account_id, form_code);
//		Map<String, Boolean> res = new HashMap<String, Boolean>();
//		res.put("build", true);
//		return ResponseEntity.ok().body(res);
//	}
	
	//extension build for line
	@GetMapping("/extension_build")
	public ResponseEntity<Map<String,Boolean>> buildExtension(
			@RequestParam(value = "account_id", required = false) String account_id,
			@RequestParam(value = "form_code", required = false) String form_code) {
		
		System.out.println("Acc Id" + account_id + " Form Code = " + form_code);
		extFieldService.buildExtensionByFormCode(account_id, form_code);
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("build", true);
		return ResponseEntity.ok().body(res);
	}
	
	
}
