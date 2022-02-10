"package com.realnet.comm.controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.comm.response.SalesResponse;" + "\r\n" + 
"import com.realnet.comm.service.salesservice;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.PageRequest;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.http.HttpHeaders;" + "\r\n" + 
"import org.springframework.http.HttpStatus;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestHeader;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
" " + "\r\n" + 
"import io.swagger.annotations.Api;" + "\r\n" + 
"import io.swagger.annotations.ApiOperation;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"@Api(tags = { \"Sales\" })" + "\r\n" + 
"public class Salescontroller {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	salesservice saleservice;" + "\r\n" + 
"" + "\r\n" + 
"	// get all data" + "\r\n" + 
"	@ApiOperation(value = \"List of sales\", response = SalesResponse.class)" + "\r\n" + 
"	@GetMapping(path = \"/getsales\")" + "\r\n" + 
"	public SalesResponse getdata(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size) {" + "\r\n" + 
"		SalesResponse resp = new SalesResponse();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<sales> result = this.saleservice.getlist(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setSales(result.getContent());" + "\r\n" + 
"" + "\r\n" + 
"		return resp;" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// @RequestParam(\"file\") MultipartFile file ," + "\r\n" + 
"	// add data" + "\r\n" + 
"	@ApiOperation(value = \"add a sales\", response = sales.class)" + "\r\n" + 
"	@PostMapping(path = \"/addsales\")" + "\r\n" + 
"	public ResponseEntity<?> savedata(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @Valid" + "\r\n" + 
"" + "\r\n" + 
"			@RequestBody sales sale) {" + "\r\n" + 
"		// String filename=file.getOriginalFilename();" + "\r\n" + 
"		// System.err.println(filename);" + "\r\n" + 
"		// sale.setUploadprofile(filename);" + "\r\n" + 
"		//" + "\r\n" + 
"		sales data = this.saleservice.createsale(sale);" + "\r\n" + 
"		if (data == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"sales not saved\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.CREATED).body(data);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// get data by id" + "\r\n" + 
"	@ApiOperation(value = \"Get a sales\", response = sales.class)" + "\r\n" + 
"	@GetMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<sales> getbyid(@PathVariable(\"id\") Integer id) {" + "\r\n" + 
"		sales getid = this.saleservice.getid(id);" + "\r\n" + 
"		if (getid == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"id not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(getid);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@ApiOperation(value = \"update a sale\", response = sales.class)" + "\r\n" + 
"	@PutMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<sales> updateTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@PathVariable(value = \"id\") Integer id, @Valid @RequestBody sales sale) {" + "\r\n" + 
"" + "\r\n" + 
"		sales updatedsale = saleservice.updateById(id, sale);" + "\r\n" + 
"		if (updatedsale == null || id != updatedsale.getSid()) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"sales not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedsale);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// DELETE" + "\r\n" + 
"	@DeleteMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		boolean delete = saleservice.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		if (delete) {" + "\r\n" + 
"			response.put(\"delete\", Boolean.TRUE);" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.OK).body(response);" + "\r\n" + 
"		}" + "\r\n" + 
"" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 