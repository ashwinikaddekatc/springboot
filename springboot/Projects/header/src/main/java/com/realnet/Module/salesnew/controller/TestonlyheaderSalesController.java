package  com.realnet.Module.salesnew.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import  com.realnet.Module.salesnew.entity.*;
import  com.realnet.Module.salesnew.responce.*;
import  com.realnet.Module.salesnew.service.*;
import com.realnet.exceptions.ResourceNotFoundException;

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
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = { "Sales" })
public class TestonlyheaderSalesController{
	@Autowired
	private Testonlyheadersalesservice saleservice;

	// get all data
	@ApiOperation(value = "List of sales", response = TestonlyheaderSalesResponce.class)
	@GetMapping(path = "/getsales")
	public TestonlyheaderSalesResponce getdata(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
		TestonlyheaderSalesResponce resp = new TestonlyheaderSalesResponce();
		Pageable paging = PageRequest.of(page, size);
		Page<Testonlyheadersales> result = this.saleservice.getlist(paging);
		resp.setPageStats(result, true);
		resp.setSales(result.getContent());

		return resp;

	}

	// @RequestParam("file") MultipartFile file ,
	// add data
	@ApiOperation(value = "add a sales", response = Testonlyheadersales.class)
	@PostMapping(path = "/addsales")
	public ResponseEntity<?> savedata(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @Valid

			@RequestBody Testonlyheadersales sale) {
		// String filename=file.getOriginalFilename();
		// System.err.println(filename);
		// sale.setUploadprofile(filename);
		//
		Testonlyheadersales data = this.saleservice.createsale(sale);
		if (data == null) {
			throw new ResourceNotFoundException("sales not saved");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}

	// get data by id
	@ApiOperation(value = "Get a sales", response = Testonlyheadersales.class)
	@GetMapping("/getsales/{id}")
	public ResponseEntity<Testonlyheadersales> getbyid(@PathVariable("id") Integer id) {
		Testonlyheadersales getid = this.saleservice.getid(id);
		if (getid == null) {
			throw new ResourceNotFoundException("id not found with id " + id);
		}
		return ResponseEntity.ok().body(getid);
	}

	// UPDATE
	@ApiOperation(value = "update a sale", response = Testonlyheadersales.class)
	@PutMapping("/getsales/{id}")
	public ResponseEntity<Testonlyheadersales> updateTeacher(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
			@PathVariable(value = "id") Integer id, @Valid @RequestBody Testonlyheadersales sale) {

		Testonlyheadersales updatedsale = saleservice.updateById(id, sale);
		if (updatedsale == null || id != updatedsale.getSid()) {
			throw new ResourceNotFoundException("sales not found with id " + id);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedsale);
	}

	// DELETE
	@DeleteMapping("/getsales/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = "id") Integer id) {
		boolean delete = saleservice.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		if (delete) {
			response.put("delete", Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}