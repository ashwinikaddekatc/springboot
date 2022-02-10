"package com.realnet.comm.controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.HashSet;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"import java.util.Set;" + "\r\n" + 
"" + "\r\n" + 
"import javax.servlet.http.HttpServletRequest;" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.PageRequest;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.data.domain.Sort;" + "\r\n" + 
"import org.springframework.http.HttpHeaders;" + "\r\n" + 
"import org.springframework.http.HttpStatus;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.jdbc.core.JdbcTemplate;" + "\r\n" + 
"import org.springframework.social.ResourceNotFoundException;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestHeader;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMethod;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"" + "\r\n" + 
"import com.google.common.net.MediaType;" + "\r\n" + 
"import com.realnet.codeextractor.entity.Rn_Bcf_Technology_Stack;" + "\r\n" + 
"import com.realnet.codeextractor.response.TechnologyStackResponse;" + "\r\n" + 
"import com.realnet.comm.entity.Customer;" + "\r\n" + 
"import com.realnet.comm.entity.Sales;" + "\r\n" + 
"import com.realnet.comm.entity.SalesPerson;" + "\r\n" + 
"import com.realnet.comm.entity.Student;" + "\r\n" + 
"import com.realnet.comm.entity.Teacher;" + "\r\n" + 
"import com.realnet.comm.repository.CustomerRepo;" + "\r\n" + 
"import com.realnet.comm.repository.SalesPersonRepo;" + "\r\n" + 
"import com.realnet.comm.repository.SalesRepo;" + "\r\n" + 
"import com.realnet.comm.response.CustomerResponse;" + "\r\n" + 
"import com.realnet.comm.response.SalesResponce;" + "\r\n" + 
"import com.realnet.comm.response.TeacherResponse;" + "\r\n" + 
"import com.realnet.comm.service.SalesService;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_Forms_Setup;" + "\r\n" + 
"//import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.fnd.response.OperationResponse;" + "\r\n" + 
"import com.realnet.fnd.response.OperationResponse.ResponseStatusEnum;" + "\r\n" + 
"import com.realnet.utils.Constant;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.Api;" + "\r\n" + 
"import io.swagger.annotations.ApiOperation;" + "\r\n" + 
"import io.swagger.annotations.ApiParam;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"//@Transactional" + "\r\n" + 
"@RequestMapping(value = \"/api\", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)" + "\r\n" + 
"@Api(tags = {\"Sales\"})" + "\r\n" + 
"" + "\r\n" + 
"public class SalesController {" + "\r\n" + 
"	" + "\r\n" + 
"	@Autowired private JdbcTemplate jdbcTemplate;" + "\r\n" + 
"	  @Autowired private SalesRepo salesRepo;" + "\r\n" + 
"	  //@Autowired private SalesPersonRepo SalesPersonRepo;" + "\r\n" + 
"	  @Autowired private SalesService salseService;" + "\r\n" + 
"	  " + "\r\n" + 
"" + "\r\n" + 
"	// GET ALL SORTED AND PAGINATED DATA" + "\r\n" + 
"	/*" + "\r\n" + 
"	 * @ApiOperation(value = \"List of sales\", response = Sales.class)" + "\r\n" + 
"	 * " + "\r\n" + 
"	 * @GetMapping(\"/sales\") public List<Sales> getTechnologyStack(" + "\r\n" + 
"	 * " + "\r\n" + 
"	 * @RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer" + "\r\n" + 
"	 * page," + "\r\n" + 
"	 * " + "\r\n" + 
"	 * @RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer" + "\r\n" + 
"	 * size) { // sorted data Pageable paging = PageRequest.of(page, size," + "\r\n" + 
"	 * Sort.by(Constant.SORT_BY_CREATION_DATE).descending()); List<Sales> result =" + "\r\n" + 
"	 * salesRepo.findAll();" + "\r\n" + 
"	 * " + "\r\n" + 
"	 * " + "\r\n" + 
"	 * " + "\r\n" + 
"	 * return result;" + "\r\n" + 
"	 * " + "\r\n" + 
"	 * " + "\r\n" + 
"	 * " + "\r\n" + 
"	 * }" + "\r\n" + 
"	 */" + "\r\n" + 
"		" + "\r\n" + 
"		//show data" + "\r\n" + 
"		@ApiOperation(value = \"List of sales\", response = SalesResponce.class)" + "\r\n" + 
"		@GetMapping(\"/sales\")" + "\r\n" + 
"		public SalesResponce getTeachers(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"				@RequestParam(value = \"size\", defaultValue = \"60\", required = false) Integer size) {" + "\r\n" + 
"			SalesResponce resp = new SalesResponce();" + "\r\n" + 
"			Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"			Page<Sales> result = salseService.getAll(paging);" + "\r\n" + 
"			resp.setPageStats(result, true);" + "\r\n" + 
"			resp.setItems(result.getContent());" + "\r\n" + 
"			return resp;" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"//		// GET BY ID" + "\r\n" + 
"//				@ApiOperation(value = \"Get a sales\", response = Teacher.class)" + "\r\n" + 
"//				@GetMapping(\"/getsales/{id}\")" + "\r\n" + 
"//				public ResponseEntity<Sales> getTeacherById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"//					Sales sales = salseService.getById(id);" + "\r\n" + 
"//					if (sales == null) {" + "\r\n" + 
"//						throw new ResourceNotFoundException(\"sales not found with id \" + id, null);" + "\r\n" + 
"//					}" + "\r\n" + 
"//					return ResponseEntity.ok().body(sales);" + "\r\n" + 
"//				}" + "\r\n" + 
"//				" + "\r\n" + 
"				" + "\r\n" + 
"				" + "\r\n" + 
"				// GET BY ID" + "\r\n" + 
"				@ApiOperation(value = \"Get a sales\", response = SalesResponce.class)" + "\r\n" + 
"				@GetMapping(\"/getsales/{id}\")" + "\r\n" + 
"				public ResponseEntity<Sales> getSalesById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"					Sales sales = salseService.getById(id);" + "\r\n" + 
"					if (sales == null) {" + "\r\n" + 
"						throw new ResourceNotFoundException(\"teacher not found with id \" + id, null);" + "\r\n" + 
"					}" + "\r\n" + 
"					return ResponseEntity.ok().body(sales);" + "\r\n" + 
"				}" + "\r\n" + 
"" + "\r\n" + 
"				" + "\r\n" + 
"				" + "\r\n" + 
"				// SAVE ADD DATA" + "\r\n" + 
"				/*" + "\r\n" + 
"				 * @ApiOperation(value = \"Add new sales\", response = Sales.class)" + "\r\n" + 
"				 * " + "\r\n" + 
"				 * @PostMapping(\"/addsales\") public ResponseEntity<Sales> createSales(" + "\r\n" + 
"				 * " + "\r\n" + 
"				 * @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String" + "\r\n" + 
"				 * authToken," + "\r\n" + 
"				 * " + "\r\n" + 
"				 * @Valid @RequestBody Sales sales) { // String userId =" + "\r\n" + 
"				 * tokenUtil.getUserId(authToken); // teacher.setCreatedBy(userId); //" + "\r\n" + 
"				 * teacher.setAccountId(userId); //sales.setSalesPerson(sales.getSalesPerson());" + "\r\n" + 
"				 * Sales savedSales = salseService.createsale(sales); if (savedSales == null) {" + "\r\n" + 
"				 * throw new ResourceNotFoundException(\"teacher is not saved\", authToken); }" + "\r\n" + 
"				 * return ResponseEntity.status(HttpStatus.CREATED).body(savedSales); }" + "\r\n" + 
"				 */" + "\r\n" + 
"				" + "\r\n" + 
"				@ApiOperation(value = \"Add A New sale\", response = Sales.class)" + "\r\n" + 
"				@PostMapping(\"/addsales\")" + "\r\n" + 
"				public ResponseEntity<Sales> createSales(" + "\r\n" + 
"						@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"						@Valid @RequestBody Sales rn_forms_setup) {" + "\r\n" + 
"					Sales savedRn_Forms_Setup = salseService.createsale(rn_forms_setup);" + "\r\n" + 
"					return ResponseEntity.status(HttpStatus.CREATED).body(savedRn_Forms_Setup);" + "\r\n" + 
"				}" + "\r\n" + 
"				" + "\r\n" + 
"				" + "\r\n" + 
"				/*@GetMapping(\"/salesperson\")" + "\r\n" + 
"				public void test(){" + "\r\n" + 
"					Sales s=new Sales();" + "\r\n" + 
"					s.setName(\"an\");" + "\r\n" + 
"					s.setDepartment(\"qqj\");" + "\r\n" + 
"					s.setJoining_date(null);" + "\r\n" + 
"					s.setStatus(\"aa\");" + "\r\n" + 
"					" + "\r\n" + 
"					" + "\r\n" + 
"					SalesPerson person=new SalesPerson();" + "\r\n" + 
"					person.setName(\"vikas\");" + "\r\n" + 
"" + "\r\n" + 
"					SalesPerson person1=new SalesPerson();" + "\r\n" + 
"					person.setName(\"vikas1\");" + "\r\n" + 
"					" + "\r\n" + 
"					Set<SalesPerson> per =new HashSet<>();" + "\r\n" + 
"					per.add(person);" + "\r\n" + 
"					per.add(person1);" + "\r\n" + 
"					s.setSalesPerson(per);" + "\r\n" + 
"					" + "\r\n" + 
"					salesRepo.save(s);" + "\r\n" + 
"				}*/" + "\r\n" + 
"				// DELETE" + "\r\n" + 
"				@DeleteMapping(\"/delsales/{id}\")" + "\r\n" + 
"				public ResponseEntity<Map<String, Boolean>> deleteSales(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"					boolean deleted = salseService.deleteById(id);" + "\r\n" + 
"					Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"					if (deleted) {" + "\r\n" + 
"						response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"						return ResponseEntity.status(HttpStatus.OK).body(response);" + "\r\n" + 
"					}" + "\r\n" + 
"					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);" + "\r\n" + 
"				}" + "\r\n" + 
"				" + "\r\n" + 
"				// UPDATE" + "\r\n" + 
"				@ApiOperation(value = \"update a sale\", response = Sales.class)" + "\r\n" + 
"				@PutMapping(\"/updatesales/{id}\")" + "\r\n" + 
"				public ResponseEntity<Sales> updateTeacher(" + "\r\n" + 
"						@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"						@PathVariable(value = \"id\") Integer id, @Valid @RequestBody Sales sale ) {" + "\r\n" + 
"					Sales updatedsale = salseService.updateById(id, sale);" + "\r\n" + 
"					if (updatedsale == null || id != updatedsale.getId()) {" + "\r\n" + 
"						throw new ResourceNotFoundException(\"sales not found with id \" + id, authToken);" + "\r\n" + 
"					}" + "\r\n" + 
"					return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedsale);" + "\r\n" + 
"				}" + "\r\n" + 
"			  " + "\r\n" + 
"		" + "\r\n" + 
"	" + "\r\n" + 
"				" + "\r\n" + 
"				" + "\r\n" + 
"				" + "\r\n" + 
"				/*" + "\r\n" + 
"		 * @GetMapping(\"/getsale/{id}\") public Sales findbyId(@PathVariable int id) {" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * return salesRepo.findAllById(null) }" + "\r\n" + 
"		 */" + "\r\n" + 
"		" + "\r\n" + 
"//		@GetMapping(\"/getsale/{id}\")" + "\r\n" + 
"//		public Sales getSaleById(@PathVariable(value=\"id\")int id)" + "\r\n" + 
"//		{" + "\r\n" + 
"//			return salesRepo.findById(id);" + "\r\n" + 
"//		}" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"//		" + "\r\n" + 
"//		//add data" + "\r\n" + 
"//		@ApiOperation(value = \"Add new customer\", response = OperationResponse.class)" + "\r\n" + 
"//	    @RequestMapping(value = \"/sales\", method = RequestMethod.POST, produces = {\"application/json\"})" + "\r\n" + 
"//	    public OperationResponse addNewSales(@RequestBody Sales sales, HttpServletRequest req) {" + "\r\n" + 
"//	        OperationResponse resp = new OperationResponse();" + "\r\n" + 
"//	        if (this.salesRepo.existsById(sales.getId()) ){" + "\r\n" + 
"//	            resp.setOperationStatus(ResponseStatusEnum.ERROR);" + "\r\n" + 
"//	            resp.setOperationMessage(\"Unable to add Customer - Customer allready exist \");" + "\r\n" + 
"//	        }" + "\r\n" + 
"//	        else{" + "\r\n" + 
"//	            Sales addedSales = this.salesRepo.save(sales);" + "\r\n" + 
"//	            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);" + "\r\n" + 
"//	            resp.setOperationMessage(\"sales Added\");" + "\r\n" + 
"//	        }" + "\r\n" + 
"//	        return resp;" + "\r\n" + 
"//	    }" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"//		//delete data" + "\r\n" + 
"//		  @ApiOperation(value = \"Delete a sales\", response = OperationResponse.class)" + "\r\n" + 
"//		    @RequestMapping(value = \"/sales/{id}\", method = RequestMethod.DELETE, produces = {\"application/json\"})" + "\r\n" + 
"//		    public OperationResponse deleteSales(@PathVariable(\"id\") Integer id, HttpServletRequest req) {" + "\r\n" + 
"//		        OperationResponse resp = new OperationResponse();" + "\r\n" + 
"//		        try {" + "\r\n" + 
"//		            if (this.salesRepo.existsById(id) ){" + "\r\n" + 
"//		                this.salesRepo.deleteById(id);" + "\r\n" + 
"//		                resp.setOperationStatus(ResponseStatusEnum.SUCCESS);" + "\r\n" + 
"//		                resp.setOperationMessage(\"Sales Deleted\");" + "\r\n" + 
"//		            }" + "\r\n" + 
"//		            else{" + "\r\n" + 
"//		                resp.setOperationStatus(ResponseStatusEnum.ERROR);" + "\r\n" + 
"//		                resp.setOperationMessage(\"No Sales Exist\");" + "\r\n" + 
"//		            }" + "\r\n" + 
"//		        }" + "\r\n" + 
"//		        catch ( Exception ge ){" + "\r\n" + 
"//		            System.out.println(\"========= MRIN GENERIC EXCEPTION ============\");" + "\r\n" + 
"//		            resp.setOperationStatus(ResponseStatusEnum.ERROR);" + "\r\n" + 
"//		            resp.setOperationMessage(ge.getMessage());" + "\r\n" + 
"//		        }" + "\r\n" + 
"//" + "\r\n" + 
"//		        return resp;" + "\r\n" + 
"//		    }" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		  " + "\r\n" + 
"		  " + "\r\n" + 
"//		// UPDATE" + "\r\n" + 
"//			@ApiOperation(value = \"update a sales\", response = Sales.class)" + "\r\n" + 
"//			@PutMapping(\"/updatesales/{id}\")" + "\r\n" + 
"//			public ResponseEntity<Sales> updateSales(" + "\r\n" + 
"//					@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"//					@PathVariable(value = \"id\") Integer id, @Valid @RequestBody Sales sales) {" + "\r\n" + 
"//" + "\r\n" + 
"//				Sales updateSales = salseService.updateById(id, sales);" + "\r\n" + 
"//				if (updateSales == null || id != updateSales.getId()) {" + "\r\n" + 
"//					throw new ResourceNotFoundException(\"teacher not found with id \" + id, authToken);" + "\r\n" + 
"//				}" + "\r\n" + 
"//				return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateSales);" + "\r\n" + 
"//			}" + "\r\n" + 
"			" + "\r\n" + 
"			" + "\r\n" + 
"		  " + "\r\n" + 
"		  " + "\r\n" + 
"		" + "\r\n" + 
"		  " + "\r\n" + 
"		  " + "\r\n" + 
"		  " + "\r\n" + 
"//		//update" + "\r\n" + 
"//		  @PutMapping(\"/sales/{id}\")" + "\r\n" + 
"//		  public ResponseEntity<Sales> updateSales(@PathVariable(value = \"id\") int id," + "\r\n" + 
"//		    @Valid @RequestBody Sales sales) throws ResourceNotFoundException {" + "\r\n" + 
"//		       Sales sal = salesRepo.findById(id)" + "\r\n" + 
"//		       .orElseThrow(() -> new ResourceNotFoundException(\"Sale not found for this id :: \" + id));" + "\r\n" + 
"//" + "\r\n" + 
"//		       sal.setName(sales.getName());" + "\r\n" + 
"//		       sal.setDepartment(sales.getDepartment());" + "\r\n" + 
"//		       sal.setJoining_date(sales.getJoining_date());" + "\r\n" + 
"//		       sal.setStatus(sales.getStatus());" + "\r\n" + 
"//		      " + "\r\n" + 
"//		       final Sales updatedSale = salesRepo.save(sales);" + "\r\n" + 
"//		       return ResponseEntity.ok(updatedSale);" + "\r\n" + 
"//		  }" + "\r\n" + 
"//		" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		}" 
"//satyam_rule_start"
"realnet project"
"//satyam_rule_end"