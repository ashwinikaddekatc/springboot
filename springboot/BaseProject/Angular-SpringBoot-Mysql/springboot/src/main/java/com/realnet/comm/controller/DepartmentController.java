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
import org.springframework.http.MediaType;
import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Sales;
import com.realnet.comm.entity.Teacher;
import com.realnet.comm.response.DepartmentResponse;
import com.realnet.comm.response.TeacherResponse;
import com.realnet.comm.service.DepartmentServiceImp;
import com.realnet.comm.service.Department_Service;
import com.realnet.exceptions.ResourceNotFoundException;

import afu.org.checkerframework.checker.units.qual.degrees;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Department" })
public class DepartmentController {
	
	@Autowired
	private Department_Service departmentServiceImp;
	
	//get all
	@ApiOperation(value = "List of Departments", response = DepartmentResponse.class)
	@GetMapping("/department")
	public DepartmentResponse getDepartments(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "40", required = false) Integer size) 
	{
		DepartmentResponse resp = new DepartmentResponse();
		Pageable paging = PageRequest.of(page, size);
		Page<Department> result = departmentServiceImp.getAll(paging);
		resp.setPageStats(result, true);
		resp.setItems(result.getContent());
		return resp;
	}
	
	// GET BY ID
		@ApiOperation(value = "Get a department", response = Department.class)
		@GetMapping("/department/{id}")
		public ResponseEntity<Department> getDepartmentId(@PathVariable(value = "id") Integer id) {
			Department department =departmentServiceImp.getbyid(id);
			if (department == null) {
				throw new ResourceNotFoundException("department not found with id " + id);
			}
			return ResponseEntity.ok().body(department);
		}
		
//		// SAVE data
//		@ApiOperation(value = "Add new department", response = Department.class)
//		@PostMapping("/departments")
//		public ResponseEntity<Department> createDepartment(
//				@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
//				@Valid @RequestBody Department dept) {
//			System.out.println("In a department controller"+dept.getDcontact());
//			Department savedDepartment = departmentServiceImp.save(dept);
//			if (savedDepartment == null) {
//				throw new ResourceNotFoundException("teacher is not saved");
//			}
//			return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
//		}
	
		//save data
		@ApiOperation(value = "Add A New sale", response = Department.class)
		@PostMapping("/department")
		public ResponseEntity<List<Department>> createSales(
				@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
				 @RequestBody List<Department> dept) 
				{
			
			List<Department> savedRn_Forms_Setup = departmentServiceImp.save(dept);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Forms_Setup);
		}
		

		 // UPDATE
		@ApiOperation(value = "update a departments", response = Teacher.class)
		@PutMapping("/department/{id}")
		public ResponseEntity<Department> updateDepartment(
				@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
				@PathVariable(value = "id") Integer id, @Valid @RequestBody Department department) {

			Department updatedDepartment = departmentServiceImp.updateById(id, department);
			if (updatedDepartment == null || id != updatedDepartment.getId()) {
				throw new ResourceNotFoundException("teacher not found with id " + id);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDepartment);
		}
		// DELETE
		@DeleteMapping("/department/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteDepartment(@PathVariable(value = "id") Integer id) {
			boolean deleted = departmentServiceImp.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
			if (deleted) {
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

}
