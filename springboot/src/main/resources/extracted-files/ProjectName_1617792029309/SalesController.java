package com.realnet.comm.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.MediaType;
import com.realnet.codeextractor.entity.Rn_Bcf_Technology_Stack;
import com.realnet.codeextractor.response.TechnologyStackResponse;
import com.realnet.comm.entity.Customer;
import com.realnet.comm.entity.Sales;
import com.realnet.comm.entity.SalesPerson;
import com.realnet.comm.entity.Student;
import com.realnet.comm.entity.Teacher;
import com.realnet.comm.repository.CustomerRepo;
import com.realnet.comm.repository.SalesPersonRepo;
import com.realnet.comm.repository.SalesRepo;
import com.realnet.comm.response.CustomerResponse;
import com.realnet.comm.response.SalesResponce;
import com.realnet.comm.response.TeacherResponse;
import com.realnet.comm.service.SalesService;
import com.realnet.fnd.entity.Rn_Forms_Setup;
//import com.realnet.exceptions.ResourceNotFoundException;
import com.realnet.fnd.response.OperationResponse;
import com.realnet.fnd.response.OperationResponse.ResponseStatusEnum;
import com.realnet.utils.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
//@Transactional
@RequestMapping(value = "/api", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Sales"})

public class SalesController {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	  @Autowired private SalesRepo salesRepo;
	  //@Autowired private SalesPersonRepo SalesPersonRepo;
	  @Autowired private SalesService salseService;
	  

		//show data
		@ApiOperation(value = "List of sales", response = SalesResponce.class)
		@GetMapping("/sales")
		public SalesResponce getTeachers(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
				@RequestParam(value = "size", defaultValue = "60", required = false) Integer size) {
			SalesResponce resp = new SalesResponce();
			Pageable paging = PageRequest.of(page, size);
			Page<Sales> result = salseService.getAll(paging);
			resp.setPageStats(result, true);
			resp.setItems(result.getContent());
			return resp;
		}
	
				
				// GET BY ID
				@ApiOperation(value = "Get a sales", response = SalesResponce.class)
				@GetMapping("/getsales/{id}")
				public ResponseEntity<Sales> getSalesById(@PathVariable(value = "id") Integer id) {
					Sales sales = salseService.getById(id);
					if (sales == null) {
						throw new ResourceNotFoundException("teacher not found with id " + id, null);
					}
					return ResponseEntity.ok().body(sales);
				}

				
				
		
				@ApiOperation(value = "Add A New sale", response = Sales.class)
				@PostMapping("/addsales")
				public ResponseEntity<Sales> createSales(
						@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
						@Valid @RequestBody Sales rn_forms_setup) {
					Sales savedRn_Forms_Setup = salseService.createsale(rn_forms_setup);
					return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Forms_Setup);
				}
			
				// DELETE
				@DeleteMapping("/delsales/{id}")
				public ResponseEntity<Map<String, Boolean>> deleteSales(@PathVariable(value = "id") Integer id) {
					boolean deleted = salseService.deleteById(id);
					Map<String, Boolean> response = new HashMap<>();
					if (deleted) {
						response.put("deleted", Boolean.TRUE);
						return ResponseEntity.status(HttpStatus.OK).body(response);
					}
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
				}
				
				// UPDATE
				@ApiOperation(value = "update a sale", response = Sales.class)
				@PutMapping("/updatesales/{id}")
				public ResponseEntity<Sales> updateTeacher(
						@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
						@PathVariable(value = "id") Integer id, @Valid @RequestBody Sales sale ) {
					Sales updatedsale = salseService.updateById(id, sale);
					if (updatedsale == null || id != updatedsale.getId()) {
						throw new ResourceNotFoundException("sales not found with id " + id, authToken);
					}
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedsale);
				}
			  
		
	
				
				

		
		
		}
