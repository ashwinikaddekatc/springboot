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
	  

	// GET ALL SORTED AND PAGINATED DATA
	/*
	 * @ApiOperation(value = "List of sales", response = Sales.class)
	 * 
	 * @GetMapping("/sales") public List<Sales> getTechnologyStack(
	 * 
	 * @RequestParam(value = "page", defaultValue = "0", required = false) Integer
	 * page,
	 * 
	 * @RequestParam(value = "size", defaultValue = "20", required = false) Integer
	 * size) { // sorted data Pageable paging = PageRequest.of(page, size,
	 * Sort.by(Constant.SORT_BY_CREATION_DATE).descending()); List<Sales> result =
	 * salesRepo.findAll();
	 * 
	 * 
	 * 
	 * return result;
	 * 
	 * 
	 * 
	 * }
	 */
		
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
		
//		// GET BY ID
//				@ApiOperation(value = "Get a sales", response = Teacher.class)
//				@GetMapping("/getsales/{id}")
//				public ResponseEntity<Sales> getTeacherById(@PathVariable(value = "id") Integer id) {
//					Sales sales = salseService.getById(id);
//					if (sales == null) {
//						throw new ResourceNotFoundException("sales not found with id " + id, null);
//					}
//					return ResponseEntity.ok().body(sales);
//				}
//				
				
				
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

				
				
				// SAVE ADD DATA
				/*
				 * @ApiOperation(value = "Add new sales", response = Sales.class)
				 * 
				 * @PostMapping("/addsales") public ResponseEntity<Sales> createSales(
				 * 
				 * @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String
				 * authToken,
				 * 
				 * @Valid @RequestBody Sales sales) { // String userId =
				 * tokenUtil.getUserId(authToken); // teacher.setCreatedBy(userId); //
				 * teacher.setAccountId(userId); //sales.setSalesPerson(sales.getSalesPerson());
				 * Sales savedSales = salseService.createsale(sales); if (savedSales == null) {
				 * throw new ResourceNotFoundException("teacher is not saved", authToken); }
				 * return ResponseEntity.status(HttpStatus.CREATED).body(savedSales); }
				 */
				
				@ApiOperation(value = "Add A New sale", response = Sales.class)
				@PostMapping("/addsales")
				public ResponseEntity<Sales> createSales(
						@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
						@Valid @RequestBody Sales rn_forms_setup) {
					Sales savedRn_Forms_Setup = salseService.createsale(rn_forms_setup);
					return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Forms_Setup);
				}
				
				
				/*@GetMapping("/salesperson")
				public void test(){
					Sales s=new Sales();
					s.setName("an");
					s.setDepartment("qqj");
					s.setJoining_date(null);
					s.setStatus("aa");
					
					
					SalesPerson person=new SalesPerson();
					person.setName("vikas");

					SalesPerson person1=new SalesPerson();
					person.setName("vikas1");
					
					Set<SalesPerson> per =new HashSet<>();
					per.add(person);
					per.add(person1);
					s.setSalesPerson(per);
					
					salesRepo.save(s);
				}*/
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
			  
		
	
				
				
				
				/*
		 * @GetMapping("/getsale/{id}") public Sales findbyId(@PathVariable int id) {
		 * 
		 * return salesRepo.findAllById(null) }
		 */
		
//		@GetMapping("/getsale/{id}")
//		public Sales getSaleById(@PathVariable(value="id")int id)
//		{
//			return salesRepo.findById(id);
//		}
		
		
//		
//		//add data
//		@ApiOperation(value = "Add new customer", response = OperationResponse.class)
//	    @RequestMapping(value = "/sales", method = RequestMethod.POST, produces = {"application/json"})
//	    public OperationResponse addNewSales(@RequestBody Sales sales, HttpServletRequest req) {
//	        OperationResponse resp = new OperationResponse();
//	        if (this.salesRepo.existsById(sales.getId()) ){
//	            resp.setOperationStatus(ResponseStatusEnum.ERROR);
//	            resp.setOperationMessage("Unable to add Customer - Customer allready exist ");
//	        }
//	        else{
//	            Sales addedSales = this.salesRepo.save(sales);
//	            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
//	            resp.setOperationMessage("sales Added");
//	        }
//	        return resp;
//	    }
		
		
		
		
//		//delete data
//		  @ApiOperation(value = "Delete a sales", response = OperationResponse.class)
//		    @RequestMapping(value = "/sales/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
//		    public OperationResponse deleteSales(@PathVariable("id") Integer id, HttpServletRequest req) {
//		        OperationResponse resp = new OperationResponse();
//		        try {
//		            if (this.salesRepo.existsById(id) ){
//		                this.salesRepo.deleteById(id);
//		                resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
//		                resp.setOperationMessage("Sales Deleted");
//		            }
//		            else{
//		                resp.setOperationStatus(ResponseStatusEnum.ERROR);
//		                resp.setOperationMessage("No Sales Exist");
//		            }
//		        }
//		        catch ( Exception ge ){
//		            System.out.println("========= MRIN GENERIC EXCEPTION ============");
//		            resp.setOperationStatus(ResponseStatusEnum.ERROR);
//		            resp.setOperationMessage(ge.getMessage());
//		        }
//
//		        return resp;
//		    }
		
		
		  
		  
//		// UPDATE
//			@ApiOperation(value = "update a sales", response = Sales.class)
//			@PutMapping("/updatesales/{id}")
//			public ResponseEntity<Sales> updateSales(
//					@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
//					@PathVariable(value = "id") Integer id, @Valid @RequestBody Sales sales) {
//
//				Sales updateSales = salseService.updateById(id, sales);
//				if (updateSales == null || id != updateSales.getId()) {
//					throw new ResourceNotFoundException("teacher not found with id " + id, authToken);
//				}
//				return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateSales);
//			}
			
			
		  
		  
		
		  
		  
		  
//		//update
//		  @PutMapping("/sales/{id}")
//		  public ResponseEntity<Sales> updateSales(@PathVariable(value = "id") int id,
//		    @Valid @RequestBody Sales sales) throws ResourceNotFoundException {
//		       Sales sal = salesRepo.findById(id)
//		       .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this id :: " + id));
//
//		       sal.setName(sales.getName());
//		       sal.setDepartment(sales.getDepartment());
//		       sal.setJoining_date(sales.getJoining_date());
//		       sal.setStatus(sales.getStatus());
//		      
//		       final Sales updatedSale = salesRepo.save(sales);
//		       return ResponseEntity.ok(updatedSale);
//		  }
//		
		
		
		
		}
